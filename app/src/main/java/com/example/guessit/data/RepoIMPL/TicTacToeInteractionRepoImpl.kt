package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.Repository.TicTacToeInteractionRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TicTacToeInteractionRepoImpl @Inject constructor(private val authInstance:FirebaseAuth
                                                       ,private val firebaseFirestore: FirebaseFirestore,
                                                       private val firebaseRealtimeDatabase: FirebaseDatabase
) : TicTacToeInteractionRepository {
    override suspend fun getTicTacToeData(roomID: String): Flow<ResultState<TicTacToeDataClass>> = callbackFlow{
          trySend(ResultState.Loading)
        try {
            firebaseFirestore.collection(Constants.TICTACTOEROOM).document(roomID).collection(
                Constants.PLAYERS).addSnapshotListener { snapshot, error ->
                if(snapshot != null){
                    val ticTacToeData = snapshot.documents.mapNotNull {
                        it?.toObject(TicTacToeDataClass::class.java)
                    }
                    trySend(ResultState.Success(ticTacToeData[0]))
                }else if(error != null){
                    trySend(ResultState.Error(error.message.toString()))

                }
            }
        }catch (e:Exception){
            trySend(ResultState.Error(e.message.toString()))
        }



    }

    override suspend fun updateTicTacToeData(ticTacToeData: TicTacToeDataClass): Flow<ResultState<String>> =callbackFlow{
        trySend(ResultState.Loading)
      try{
          firebaseFirestore.collection(Constants.TICTACTOEROOM).document(ticTacToeData.player1LeaderUID).collection(Constants.PLAYERS)
              .document(ticTacToeData.player1LeaderUID).set(ticTacToeData).addOnSuccessListener {
                  trySend(ResultState.Success("Updated"))
              }.addOnFailureListener {
                  trySend(ResultState.Error(it.message.toString()))
              }
      }catch (e: Exception){

      }
    }
}