package com.example.guessit.data.RepoIMPL

import android.util.Log
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth):Repository  {
    //repo
    private val currentUserAuth= authInstance.currentUser?.uid?:""
    override suspend fun signUpUser(email:String , password:String): Flow<ResultState<String>> = callbackFlow{
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

    override suspend fun loginUser(email:String , password:String): Flow<ResultState<String>> = callbackFlow {
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
}