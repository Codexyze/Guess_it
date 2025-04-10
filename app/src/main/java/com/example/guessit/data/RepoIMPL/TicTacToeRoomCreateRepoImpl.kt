package com.example.guessit.data.RepoIMPL

import android.util.Log
import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.Repository.TicTacToeRoomCreateRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TicTacToeRoomCreateRepoImpl @Inject constructor (
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore : FirebaseFirestore
): TicTacToeRoomCreateRepository {
    val currentUserAuth = firebaseAuth.currentUser?.uid?.toString() ?: ""

    override suspend fun ticTacToeCreateRoom(playerName: String): Flow<ResultState<String>> = callbackFlow{
        val ticTacToeData = TicTacToeDataClass(
            player1 = currentUserAuth
            , player1name = playerName
        )
        trySend(ResultState.Loading)
        firebaseFireStore.collection(Constants.TICTACTOEROOM).document(currentUserAuth)
            .collection(Constants.PLAYERS).document(currentUserAuth).set(ticTacToeData).addOnSuccessListener {
                trySend(ResultState.Success("Room Created"))
                Log.d("Firebase", "TicTactoe Room created successfully")
            }.addOnFailureListener {
                trySend(ResultState.Error("TicTacToe Room  Failed to Create Room"))
                Log.d("Firebase", "${it.message}")
            }
        awaitClose {
            close()
        }
    }

}