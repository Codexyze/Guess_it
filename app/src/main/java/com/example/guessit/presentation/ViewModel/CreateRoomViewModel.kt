package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.CreateRoomState
import com.example.guessit.domain.StateHandeling.ResultState
import com.example.guessit.domain.UseCases.UseCasesAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRoomViewModel @Inject constructor(
    private val useCaseAcess: UseCasesAccess): ViewModel(){
    private val _createRoomState = MutableStateFlow(CreateRoomState())
    val createRoomState = _createRoomState.asStateFlow()

    fun createRoom(player:Player){
        viewModelScope.launch {
            useCaseAcess.createRoomFromServerUseCase.createRoomFromServerUseCase(playerData = player).collectLatest {result->
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
}