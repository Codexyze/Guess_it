package com.example.guessit.data.RepoIMPL

import android.util.Log
import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.CreateRoomRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CreateRoomRepositoryImpl@Inject constructor(private val authInstance:FirebaseAuth
                                                  ,private val firebaseFirestore: FirebaseFirestore,
                                                  private val firebaseRealtimeDatabase: FirebaseDatabase
): CreateRoomRepository {
    private val currentUserAuth= authInstance.currentUser?.uid?:""
    override suspend fun createRoomFromServer(playerData: Player): Flow<ResultState<String>>  = callbackFlow{
        trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document(currentUserAuth)
            .collection(Constants.PLAYERS).document(currentUserAuth).set(playerData).addOnSuccessListener {
                trySend(ResultState.Success("Room Created"))
                Log.d("Firebase", "Room created successfully")
            }.addOnFailureListener {
                trySend(ResultState.Error("Failed to Create Room"))
                Log.d("Firebase", "${it.message}")
            }
        awaitClose {
            close()
        }
    }
}