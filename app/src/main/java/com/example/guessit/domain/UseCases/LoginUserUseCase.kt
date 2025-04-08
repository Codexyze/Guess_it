package com.example.guessit.domain.UseCases

import com.example.guessit.domain.Repository.UserAuthenticationRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class LoginUserUseCase(private val repository: UserAuthenticationRepository) {

    suspend fun loginUserUseCase(email:String , password:String):Flow<ResultState<String>>{
        return repository.loginUser(email = email, password = password)
    }
}