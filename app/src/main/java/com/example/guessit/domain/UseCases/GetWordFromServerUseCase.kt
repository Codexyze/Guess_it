package com.example.guessit.domain.UseCases

import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class GetWordFromServerUseCase(private val repository: Repository) {
    suspend fun getWordFromServerUseCase():Flow<ResultState<List<String>>>{
        return  repository.getWordFromServer()
    }
}