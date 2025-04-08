package com.example.guessit.domain.Repository

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface GetPlayersRepository {
    suspend fun getAllPlayersFromRoom(roomID: String):Flow<ResultState<List<Player>>>//
}