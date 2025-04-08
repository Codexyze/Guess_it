package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.GetPlayersRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class GetAllPlayersFromRoomUseCase(private val repository: GetPlayersRepository) {
    suspend fun getAllPlayersFromRoomUseCase(roomID: String):Flow<ResultState<List<Player>>>{
        return repository.getAllPlayersFromRoom(roomID)
    }
}