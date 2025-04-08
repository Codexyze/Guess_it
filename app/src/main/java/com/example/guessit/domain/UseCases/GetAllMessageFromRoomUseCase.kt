package com.example.guessit.domain.UseCases

import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.domain.Repository.MessagesRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class GetAllMessageFromRoomUseCase(private val repository: MessagesRepository) {
    suspend fun getAllMessagesFromRoom(roomID: String):Flow<ResultState<List<Message>>>{
        return repository.getAllMessagesFromRoom(roomID)
    }
}