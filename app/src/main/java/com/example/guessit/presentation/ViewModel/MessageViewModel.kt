package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.domain.StateHandeling.GetAllMessageFromRoomState
import com.example.guessit.domain.StateHandeling.ResultState
import com.example.guessit.domain.StateHandeling.SendMessageToRoomMembersState
import com.example.guessit.domain.UseCases.UseCasesAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val useCaseAcess: UseCasesAccess):ViewModel ()
{
    private  val _sendMessageToRoomMembersState = MutableStateFlow(SendMessageToRoomMembersState())
    val sendMessageToRoomMembersState =_sendMessageToRoomMembersState.asStateFlow()
    private val _getAllMessageFromRoomState = MutableStateFlow(GetAllMessageFromRoomState())
    val getAllMessageFromRoomState = _getAllMessageFromRoomState.asStateFlow()

    fun sendMessageToRoomMembers(roomID: String,message: Message){
        viewModelScope.launch {
            useCaseAcess.sendMessageToAllRoomMemberUseCase.sendMessageToAllRoomMembersUseCase(roomID = roomID, message = message).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _sendMessageToRoomMembersState.value = SendMessageToRoomMembersState(isLoading = true)
                    }
                    is ResultState.Error->{
                        _sendMessageToRoomMembersState.value = SendMessageToRoomMembersState(
                            isLoading = false, error = result.message
                        )
                    }
                    is ResultState.Success->{
                        _sendMessageToRoomMembersState.value =SendMessageToRoomMembersState(
                            isLoading = false, data = result.data
                        )
                    }
                }
            }
        }
    }

    fun getAllMessageFromRoom(roomID: String){
        viewModelScope.launch {
            useCaseAcess.getAllMessageFromRoomUseCase.getAllMessagesFromRoom(roomID = roomID).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _getAllMessageFromRoomState.value = GetAllMessageFromRoomState(
                            isLoading = true
                        )
                    }
                    is ResultState.Success->{
                        _getAllMessageFromRoomState.value =GetAllMessageFromRoomState(
                            isLoading = false, data = result.data
                        )

                    }
                    is ResultState.Error->{
                        _getAllMessageFromRoomState.value =GetAllMessageFromRoomState(
                            isLoading = false, error = result.message
                        )

                    }
                }

            }
        }

    }
}