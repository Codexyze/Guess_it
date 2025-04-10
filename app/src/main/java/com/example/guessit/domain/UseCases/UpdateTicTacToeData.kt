package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.Repository.TicTacToeInteractionRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class UpdateTicTacToeDataUseCase(private val repository: TicTacToeInteractionRepository) {
    suspend fun updateTicTacToeData(ticTacToeData: TicTacToeDataClass): Flow<ResultState<String>> {
        return repository.updateTicTacToeData(ticTacToeData)
    }

}