package com.example.guessit.domain.Repository

import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface TicTacToeRoomCreateRepository {
    suspend fun ticTacToeCreateRoom(playerName: String):Flow<ResultState<String>>
}