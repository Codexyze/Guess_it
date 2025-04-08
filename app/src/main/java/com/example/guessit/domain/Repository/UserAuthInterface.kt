package com.example.guessit.domain.Repository

import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow


interface UserAuthenticationRepository {
    suspend fun loginUser(email:String , password:String):Flow<ResultState<String>>//
    suspend fun signUpUser(email: String, password: String): Flow<ResultState<String>>
}