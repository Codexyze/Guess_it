package com.example.guessit.domain.UseCases


import com.example.guessit.domain.Repository.WordsRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class GetWordFromServerUseCase(private val repository: WordsRepository) {
    suspend fun getWordFromServerUseCase():Flow<ResultState<List<String>>>{
        return  repository.getWordFromServer()
    }
}