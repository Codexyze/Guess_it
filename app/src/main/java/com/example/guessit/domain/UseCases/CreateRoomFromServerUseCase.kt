package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.CreateRoomRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class CreateRoomFromServerUseCase(private val repository: CreateRoomRepository) {
    suspend fun createRoomFromServerUseCase(playerData:Player):Flow<ResultState<String>>{
        return repository.createRoomFromServer(playerData)
    }
}