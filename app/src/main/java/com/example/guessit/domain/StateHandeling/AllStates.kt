package com.example.guessit.domain.StateHandeling

sealed class ResultState<out T>{
    object Loading : ResultState<Nothing>()
    data class Success<T>(val data : T) : ResultState<T>()
    data class Error(val message : String) : ResultState<Nothing>()
}

data class SignUpState(val isLoading:Boolean = false,
        val data:String? = null,
        val error:String? = null)

data class LoginState(val isLoading:Boolean = false,
                       val data:String? = null,
                       val error:String? = null)