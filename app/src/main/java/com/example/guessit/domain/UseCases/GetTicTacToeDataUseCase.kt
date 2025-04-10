package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.Repository.TicTacToeInteractionRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class GetTicTacToeDataUseCase (private val repository: TicTacToeInteractionRepository) {
    suspend fun getTicTacToeDataUseCase(roomID: String):Flow<ResultState<TicTacToeDataClass>>{
        return  repository.getTicTacToeData(roomID = roomID)
    }

}