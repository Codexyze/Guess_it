package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.domain.Repository.WordsRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth
                                              ,private val firebaseFirestore: FirebaseFirestore,
                                              private val firebaseRealtimeDatabase: FirebaseDatabase
): WordsRepository{
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
}