package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.JoinRoomRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class JoinRoomWithIDUseCase(private val repository: JoinRoomRepository) {
    suspend fun joinRoomWithIDUseCase(roomID:String,player: Player):Flow<ResultState<String>>{
        return repository.joinRoomWithID(roomID,player)
    }
}