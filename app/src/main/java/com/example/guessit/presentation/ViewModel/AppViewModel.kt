package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.CreateRoomState
import com.example.guessit.domain.StateHandeling.GetWordFromServerState
import com.example.guessit.domain.StateHandeling.JoinRoomState
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
    private val _getWordFromServerState= MutableStateFlow(GetWordFromServerState())
    val getWordFromServerSate = _getWordFromServerState.asStateFlow()
    private val _createRoomState = MutableStateFlow(CreateRoomState())
    val createRoomState = _createRoomState.asStateFlow()
    private val _joinRoomState = MutableStateFlow(JoinRoomState())
    val joinRoomState = _joinRoomState.asStateFlow()

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

    fun getWordFromServer(){
        viewModelScope.launch {
            repositoryImpl.getWordFromServer().collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _getWordFromServerState.value = GetWordFromServerState(
                            isLoading = true
                        )
                    }
                    is ResultState.Error->{
                        _getWordFromServerState.value =GetWordFromServerState(
                            isLoading = false, error = result.message
                        )
                    }
                    is ResultState.Success->{
                        _getWordFromServerState.value =GetWordFromServerState(
                            isLoading = false,
                            data = result.data
                        )
                    }
                }

            }

        }
    }

    fun createRoom(player:Player){
        viewModelScope.launch {
            repositoryImpl.createRoomFromServer(playerData = player).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                       _createRoomState.value = CreateRoomState(isLoading = true)
                    }
                    is ResultState.Success->{
                        _createRoomState.value = CreateRoomState(isLoading = false,  data = result.data)
                    }
                    is ResultState.Error->{
                        _createRoomState.value = CreateRoomState(isLoading = false, error = result.message)
                    }
                }
            }
        }

    }

    suspend fun joinRoomUsingUserID(roomID:String ,player:Player){
        viewModelScope.launch {
            repositoryImpl.joinRoomWithID(roomID = roomID, player = player).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _joinRoomState.value = JoinRoomState(isLoading = true)
                    }
                    is ResultState.Error->{
                        _joinRoomState.value = JoinRoomState(
                            isLoading = false , error = result.message
                        )
                    }
                    is ResultState.Success->{
                        _joinRoomState.value = JoinRoomState(
                            isLoading = false, data = result.data
                        )
                    }
                }
            }
        }
    }

}
