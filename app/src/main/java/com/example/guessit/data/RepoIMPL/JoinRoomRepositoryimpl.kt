package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.JoinRoomRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class JoinRoomRepositoryimpl @Inject constructor(private val authInstance:FirebaseAuth
                                                ,private val firebaseFirestore: FirebaseFirestore,
                                                private val firebaseRealtimeDatabase: FirebaseDatabase
): JoinRoomRepository {
    override suspend fun joinRoomWithID(
        roomID: String,
        player: Player
    ): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document(roomID).collection(Constants.PLAYERS).add(player)
            .addOnSuccessListener {
                trySend(ResultState.Success("Joined"))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }
    }

}