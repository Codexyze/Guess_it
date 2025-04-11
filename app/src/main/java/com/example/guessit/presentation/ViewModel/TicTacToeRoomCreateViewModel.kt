package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.StateHandeling.CreateRoomTicTacToeState
import com.example.guessit.domain.StateHandeling.GetTicTacToeDataState
import com.example.guessit.domain.StateHandeling.JoinTTTRoomState
import com.example.guessit.domain.StateHandeling.ResultState
import com.example.guessit.domain.StateHandeling.UpdateTicTacToeDataState
import com.example.guessit.domain.UseCases.UseCasesAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicTacToeRoomCreateViewModel @Inject constructor(private val usecase: UseCasesAccess): ViewModel() {
    private val _createRoomTicTacToeState = MutableStateFlow(CreateRoomTicTacToeState())
     val createRoomTicTacToeState = _createRoomTicTacToeState.asStateFlow()
    private val _joinTTTRoomState = MutableStateFlow(JoinTTTRoomState())
    val joinTTTRoomState = _joinTTTRoomState.asStateFlow()
    private val _ticTacToeDataState = MutableStateFlow( GetTicTacToeDataState())
    val ticTacToeDataState = _ticTacToeDataState.asStateFlow()
    private val _updateTicTacToeDataState = MutableStateFlow(UpdateTicTacToeDataState())
    val updateTicTacToeDataState = _updateTicTacToeDataState.asStateFlow()

    fun createTicTacToeRoom(playerName: String){
       viewModelScope.launch {
           usecase.createTicTacToeRoom.ticTacToeRoomCreateUseCase(playerName = playerName).collectLatest {result->
               when(result){
                   is ResultState.Loading->{
                       _createRoomTicTacToeState.value = CreateRoomTicTacToeState(isLoading = true)
                   }
                   is ResultState.Success->{
                       _createRoomTicTacToeState.value = CreateRoomTicTacToeState(data = result.data, isLoading = false)
                   }
                   is ResultState.Error->{
                       _createRoomTicTacToeState.value = CreateRoomTicTacToeState(error = result.message, isLoading = false)
                   }
               }

           }

       }

    }
    fun joinTicTacToeRoom(roomID:String,playerName: String){

        viewModelScope.launch {
           usecase.joinTicTacToeUseCase .joinTTTRoomWithIdUseCase(roomID = roomID, playerName = playerName).collectLatest {result->
               when(result){
                   is ResultState.Loading->{
                       _joinTTTRoomState.value = JoinTTTRoomState(isLoading = true)
                   }
                   is ResultState.Success->{
                       _joinTTTRoomState.value = JoinTTTRoomState(isLoading = false,data = result.data)
                   }
                   is ResultState.Error->{
                       _joinTTTRoomState.value = JoinTTTRoomState(isLoading = false, error = result.message)
                   }
               }
           }
        }
    }

    fun getTTTdataWithID(roomID: String){
        viewModelScope.launch {
            usecase.getTicTacToeDAtaUsecase.getTicTacToeDataUseCase(roomID = roomID).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _ticTacToeDataState.value = GetTicTacToeDataState(isLoading = true)
                    }
                    is ResultState.Success->{
                        _ticTacToeDataState.value = GetTicTacToeDataState(isLoading = false, data = result.data)
                    }
                    is ResultState.Error->{
                        _ticTacToeDataState.value = GetTicTacToeDataState(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun updateTicTacToeData(ticTacToeData: TicTacToeDataClass){
        viewModelScope.launch {
            usecase.updateTicTacToeDataUseCase.updateTicTacToeData(ticTacToeData = ticTacToeData).collectLatest {result->
                when(result){

                    is ResultState.Loading->{
                        _updateTicTacToeDataState.value = UpdateTicTacToeDataState(isLoading = true)
                    }
                    is ResultState.Success->{
                        _updateTicTacToeDataState.value = UpdateTicTacToeDataState(isLoading = false, data = result.data)
                    }
                    is ResultState.Error->{
                        _updateTicTacToeDataState.value = UpdateTicTacToeDataState(isLoading = false, error = result.message)
                    }
                }

            }
        }

    }

}