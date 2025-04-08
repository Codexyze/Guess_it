package com.example.guessit.domain.Repository

import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    suspend fun getWordFromServer():Flow<ResultState<List<String>>>//
}