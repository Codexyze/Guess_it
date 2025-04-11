package com.example.guessit.domain.UseCases

import com.example.guessit.domain.Repository.TicTacToeInteractionRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JoinTTTRoomWithIdUseCase @Inject constructor(private val repository : TicTacToeInteractionRepository) {

    suspend fun joinTTTRoomWithIdUseCase(roomID:String,playerName: String):Flow<ResultState<String>>{
        return repository.joinTTTRoomWithID(roomID = roomID, playerName = playerName)

    }
}