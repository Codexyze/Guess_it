package com.example.guessit.domain.Repository

import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun signUpUser(email:String , password:String):Flow<ResultState<String>>
    suspend fun loginUser(email:String , password:String):Flow<ResultState<String>>
    suspend fun getWordFromServer():Flow<ResultState<List<String>>>
}