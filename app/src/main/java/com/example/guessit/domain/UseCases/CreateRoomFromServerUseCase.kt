package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class CreateRoomFromServerUseCase(private val repository: Repository) {
    suspend fun createRoomFromServerUseCase(playerData:Player):Flow<ResultState<String>>{
        return repository.createRoomFromServer(playerData)
    }
}