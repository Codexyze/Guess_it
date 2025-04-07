package com.example.guessit.domain.UseCases

import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class SignUpUserUseCase(private  val repository: Repository) {
    suspend fun signUpUserUseCase(email:String , password:String):Flow<ResultState<String>>{
        return repository.signUpUser(email = email, password = password)
    }
}