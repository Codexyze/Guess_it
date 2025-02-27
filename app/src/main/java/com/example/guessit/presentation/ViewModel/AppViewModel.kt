package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.example.guessit.domain.StateHandeling.LoginState
import com.example.guessit.domain.StateHandeling.ResultState
import com.example.guessit.domain.StateHandeling.SignUpState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    private val authInstance:FirebaseAuth):ViewModel ()
{
        private val _signUpState = MutableStateFlow(SignUpState())
        val signUpState = _signUpState.asStateFlow()
       private val _loginState = MutableStateFlow(LoginState())
        val loginState = _loginState.asStateFlow()

    fun signUp(email:String , password:String){
        viewModelScope.launch {
            repositoryImpl.signUpUser(email = email, password = password).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _signUpState.value = SignUpState(isLoading = true)
                    }
                    is ResultState.Success->{
                        _signUpState.value = SignUpState(isLoading = false, data = result.data)
                    }
                    is ResultState.Error->{
                        _signUpState.value = SignUpState(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun login(email:String ,password:String){
        viewModelScope.launch {
            repositoryImpl.loginUser(email = email, password = password).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _loginState.value = LoginState(isLoading = true)
                    }
                    is ResultState.Success->{
                        _loginState.value = LoginState(isLoading = false, data = result.data)
                    }
                    is ResultState.Error ->{
                        _loginState.value =  LoginState(isLoading = false, error = result.message)
                    }
                }
            }
        }

    }


}