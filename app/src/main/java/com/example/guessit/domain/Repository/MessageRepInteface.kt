package com.example.guessit.domain.Repository

import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    suspend fun sendMessageToAllRoomMembers(roomID:String,message: Message):Flow<ResultState<String>>
    suspend fun getAllMessagesFromRoom(roomID: String):Flow<ResultState<List<Message>>>
}