package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.JoinRoomState
import com.example.guessit.domain.StateHandeling.ResultState
import com.example.guessit.domain.UseCases.UseCasesAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JoinRoomViewModel @Inject constructor(
    private val useCaseAcess: UseCasesAccess):ViewModel ()
{
    private val _joinRoomState = MutableStateFlow(JoinRoomState())
    val joinRoomState = _joinRoomState.asStateFlow()

    fun joinRoomUsingUserID(roomID:String ,player:Player){
        viewModelScope.launch {
            useCaseAcess.joinRoomWithIDUseCase.joinRoomWithIDUseCase(roomID = roomID, player = player).collectLatest {result->
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