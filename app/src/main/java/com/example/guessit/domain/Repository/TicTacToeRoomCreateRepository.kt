package com.example.guessit.domain.Repository

import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface TicTacToeRoomCreateRepository {
    suspend fun CreateRoomRepository(roomID: String):Flow<ResultState<String>>
}