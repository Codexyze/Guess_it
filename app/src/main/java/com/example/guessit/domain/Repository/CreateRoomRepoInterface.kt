package com.example.guessit.domain.Repository

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow


interface CreateRoomRepository {
    suspend fun createRoomFromServer(playerData:Player):Flow<ResultState<String>>//
}