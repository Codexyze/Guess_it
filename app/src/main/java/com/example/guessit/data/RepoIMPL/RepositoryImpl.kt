package com.example.guessit.data.RepoIMPL

import android.util.Log
import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth,private val firebaseFirestore: FirebaseFirestore):Repository  {
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

    override suspend fun getWordFromServer(): Flow<ResultState<List<String>>> = callbackFlow{
       trySend(ResultState.Loading)
        try {
            firebaseFirestore.collection(Constants.WORDS).document(Constants.LIST).get().addOnSuccessListener {
                val data = it.data?.get(Constants.WORDSFIELD) as? List<String> ?: emptyList()
                trySend(ResultState.Success(data))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.message.toString()))
            }

        }catch (e:Exception){
            trySend(ResultState.Error(e.message.toString()))
            ///Error handeling

        }
        awaitClose {
            close()
        }

    }

    override suspend fun createRoomFromServer(playerData: Player): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document(currentUserAuth)
            .collection(Constants.PLAYERS).document(currentUserAuth).set(playerData).addOnSuccessListener {
                trySend(ResultState.Success("Room Created"))
            }.addOnFailureListener {
                trySend(ResultState.Error("Failed to Create Room"))
            }
        awaitClose {
            close()
        }
    }

    override suspend fun joinRoomWithID(roomID: String,player: Player): Flow<ResultState<String>> = callbackFlow{
       trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document().collection(Constants.PLAYERS).add(player)
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
