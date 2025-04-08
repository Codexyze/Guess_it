package com.example.guessit.data.RepoIMPL

import android.util.Log
import com.example.guessit.domain.Repository.UserAuthenticationRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth
                                                 ,private val firebaseFirestore: FirebaseFirestore,
                                                 private val firebaseRealtimeDatabase: FirebaseDatabase
): UserAuthenticationRepository {
    override suspend fun loginUser(
        email: String,
        password: String
    ): Flow<ResultState<String>>  = callbackFlow {
        trySend(ResultState.Loading)
        authInstance.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            trySend(ResultState.Success(it.user?.uid.toString()))
            close()
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
            close()
        }
        awaitClose {
            close()
        }
    }



    override suspend fun signUpUser(
        email: String,
        password: String
    ): Flow<ResultState<String>>  = callbackFlow{
        trySend(ResultState.Loading)
        authInstance.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            trySend(ResultState.Success(it.user?.uid.toString()))
            Log.d("SIGNUP","SUCESSFULL")
            close()
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
            Log.d("SIGNUP","ERROR")
            close()

        }
        awaitClose {
            close()
        }
    }
}