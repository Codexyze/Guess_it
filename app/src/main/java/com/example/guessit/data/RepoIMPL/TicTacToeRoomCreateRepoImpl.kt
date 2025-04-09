package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.domain.Repository.TicTacToeRoomCreateRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TicTacToeRoomCreateRepoImpl @Inject constructor (
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore : FirebaseFirestore
): TicTacToeRoomCreateRepository {
    override suspend fun CreateRoomRepository(roomID: String): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseFireStore.collection(Constants.TICTACTOEROOM).document()


    }
}