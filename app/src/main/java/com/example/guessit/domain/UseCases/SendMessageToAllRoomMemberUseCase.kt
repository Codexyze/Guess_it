package com.example.guessit.domain.UseCases

import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class SendMessageToAllRoomMemberUseCase(private val repository: Repository) {
    suspend fun sendMessageToAllRoomMembersUseCase(roomID:String,message: Message):Flow<ResultState<String>>{
        return repository.sendMessageToAllRoomMembers(roomID,message)
    }
}