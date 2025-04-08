package com.example.guessit.domain.Repository

import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface Repository {
    //Features
    suspend fun signUpUser(email:String , password:String):Flow<ResultState<String>>//
    suspend fun loginUser(email:String , password:String):Flow<ResultState<String>>//
    suspend fun getWordFromServer():Flow<ResultState<List<String>>>//
    suspend fun createRoomFromServer(playerData:Player):Flow<ResultState<String>>//
    suspend fun joinRoomWithID(roomID:String,player: Player):Flow<ResultState<String>>//
    suspend fun getAllPlayersFromRoom(roomID: String):Flow<ResultState<List<Player>>>//
    suspend fun uploadAllPlayersCanvasPoints(lineCordinates:Lines,roomID: String):Flow<ResultState<String>>//
    suspend fun uploadLineTorealTimeDatabase(lines:LiveLine,roomID: String):Flow<ResultState<String>>//
    suspend fun getRealtimeLines(roomID: String):Flow<ResultState<LiveLine>>//
    suspend fun sendMessageToAllRoomMembers(roomID:String,message: Message):Flow<ResultState<String>>
    suspend fun getAllMessagesFromRoom(roomID: String):Flow<ResultState<List<Message>>>
}

interface UserAuthenticationRepository {
    suspend fun loginUser(email:String , password:String):Flow<ResultState<String>>//
    suspend fun signUpUser(email: String, password: String): Flow<ResultState<String>>
}

interface CreateRoomRepository {
    suspend fun createRoomFromServer(playerData:Player):Flow<ResultState<String>>//
}
interface JoinRoomRepository {
    suspend fun joinRoomWithID(roomID:String,player: Player):Flow<ResultState<String>>//
}
interface WordsRepository {
    suspend fun getWordFromServer():Flow<ResultState<List<String>>>//
}
interface GetPlayersRepository {
    suspend fun getAllPlayersFromRoom(roomID: String):Flow<ResultState<List<Player>>>//
}
interface UploadLinesRepository {
    suspend fun uploadAllPlayersCanvasPoints(lineCordinates:Lines,roomID: String):Flow<ResultState<String>>//
    suspend fun uploadLineTorealTimeDatabase(lines:LiveLine,roomID: String):Flow<ResultState<String>>//
}
interface GetRealTimeLinesRepository {
    suspend fun getRealtimeLines(roomID: String):Flow<ResultState<LiveLine>>//
}
interface MessagesRepository {
    suspend fun sendMessageToAllRoomMembers(roomID:String,message: Message):Flow<ResultState<String>>
    suspend fun getAllMessagesFromRoom(roomID: String):Flow<ResultState<List<Message>>>
}