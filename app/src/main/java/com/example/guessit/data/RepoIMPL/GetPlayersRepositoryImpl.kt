package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.GetPlayersRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetPlayersRepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth
                                                   ,private val firebaseFirestore: FirebaseFirestore,
                                                   private val firebaseRealtimeDatabase: FirebaseDatabase
): GetPlayersRepository {
    override suspend fun getAllPlayersFromRoom(roomID: String): Flow<ResultState<List<Player>>>  = callbackFlow {
        trySend(ResultState.Loading)

        val listenerRegistration = firebaseFirestore.collection(Constants.ROOM)
            .document(roomID)
            .collection(Constants.PLAYERS)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(ResultState.Error(error.message ?: "Unknown error"))
                }

                if (snapshot != null) {
                    val players = snapshot.documents.mapNotNull { it.toObject(Player::class.java) }
                    trySend(ResultState.Success(players))
                }
            }

        awaitClose {
            close()
        }
    }

}