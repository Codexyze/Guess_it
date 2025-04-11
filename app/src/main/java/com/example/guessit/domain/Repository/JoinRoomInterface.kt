package com.example.guessit.domain.Repository

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface JoinRoomRepository {
    suspend fun joinRoomWithID(roomID: String, player: Player):Flow<ResultState<String>>//
}