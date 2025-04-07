package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.CreateRoomState
import com.example.guessit.domain.StateHandeling.GetAllMessageFromRoomState
import com.example.guessit.domain.StateHandeling.GetAllPlayerInRoomState
import com.example.guessit.domain.StateHandeling.GetRealTimeLines
import com.example.guessit.domain.StateHandeling.GetWordFromServerState
import com.example.guessit.domain.StateHandeling.JoinRoomState
import com.example.guessit.domain.StateHandeling.LoginState
import com.example.guessit.domain.StateHandeling.ResultState
import com.example.guessit.domain.StateHandeling.SendMessageToRoomMembersState
import com.example.guessit.domain.StateHandeling.SignUpState
import com.example.guessit.domain.StateHandeling.UploadLineCordinatesState
import com.example.guessit.domain.StateHandeling.UploadLinesToRealTimeDataBaseState
import com.example.guessit.domain.UseCases.UseCasesAccess
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
    private val useCaseAcess: UseCasesAccess,
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
    private  val _getAllPlayersFromRoomState = MutableStateFlow(GetAllPlayerInRoomState())
    val getAllPlayersFromRoomState = _getAllPlayersFromRoomState.asStateFlow()
    private val _uploadLiveLineCordinatesState = MutableStateFlow(UploadLineCordinatesState())
    val uploadLiveLineCordinatesSate = _uploadLiveLineCordinatesState.asStateFlow()
    private  val _uploadToRealTimeDatabaseState = MutableStateFlow(UploadLinesToRealTimeDataBaseState())
    val uploadToRealTimeDatabaseState = _uploadToRealTimeDatabaseState.asStateFlow()
    private val _getRealtimeLinesState = MutableStateFlow(GetRealTimeLines())
    val getRealtimeLinesState = _getRealtimeLinesState.asStateFlow()
    private  val _sendMessageToRoomMembersState = MutableStateFlow(SendMessageToRoomMembersState())
    val sendMessageToRoomMembersState =_sendMessageToRoomMembersState.asStateFlow()
    private val _getAllMessageFromRoomState = MutableStateFlow(GetAllMessageFromRoomState())
    val getAllMessageFromRoomState = _getAllMessageFromRoomState.asStateFlow()


    fun signUp(email:String , password:String){
        viewModelScope.launch {
            useCaseAcess.signUpUserUseCase.signUpUserUseCase(email = email, password = password).collectLatest {result->
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
            useCaseAcess.loginUserUseCase.loginUserUseCase(email = email, password = password).collectLatest {result->
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
            useCaseAcess.getWordFromServerUseCase.getWordFromServerUseCase().collectLatest {result->
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
    fun getAllPlayersFromRoom(roomID: String){
        viewModelScope.launch {
            useCaseAcess.getAllPlayersFromRoomUseCase.getAllPlayersFromRoomUseCase(roomID = roomID).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _getAllPlayersFromRoomState.value = GetAllPlayerInRoomState(isLoading = true)
                    }
                    is ResultState.Success->{
                        _getAllPlayersFromRoomState.value = GetAllPlayerInRoomState(isLoading = false,
                            data = result.data)
                    }
                    is ResultState.Error->{
                        _getAllPlayersFromRoomState.value =GetAllPlayerInRoomState(
                            isLoading = false, error = result.message
                        )
                    }
                }
            }
        }

    }

    fun uploadLiveLineCordinates(lineCordinates:Lines,roomID: String){
        viewModelScope.launch {
            useCaseAcess.uploadAllPlayersCanvasPoints.uploadAllPlayersCanvasPointsUseCase(lineCordinates = lineCordinates, roomID = roomID).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _uploadLiveLineCordinatesState.value = UploadLineCordinatesState(
                            isLoading = true
                        )
                    }
                    is ResultState.Error->{
                        _uploadLiveLineCordinatesState.value = UploadLineCordinatesState(
                            isLoading = false, error = result.message
                        )
                    }
                    is ResultState.Success->{
                        _uploadLiveLineCordinatesState.value = UploadLineCordinatesState(
                            isLoading = false, data = result.data
                        )
                    }
                }
            }
        }
    }
    fun uploadLiveLinesToRealTimeDatabase(lines:LiveLine,roomID: String){
        viewModelScope.launch {
            useCaseAcess.uploadLineToRealTimeDataBaseUseCase.uploadLineTorealTimeDatabaseUseCase(lines = lines, roomID = roomID).collectLatest {result->
                when(result){
                    is ResultState.Error->{
                        _uploadToRealTimeDatabaseState.value = UploadLinesToRealTimeDataBaseState(
                            isLoading = false, error = result.message
                        )
                    }
                    is ResultState.Success->{
                        _uploadToRealTimeDatabaseState.value = UploadLinesToRealTimeDataBaseState(
                            isLoading = false , data =  result.data
                        )
                    }
                    is ResultState.Loading->{
                        _uploadToRealTimeDatabaseState.value =UploadLinesToRealTimeDataBaseState(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun getRealtimeLines(roomID: String){
        viewModelScope.launch {
            useCaseAcess.getRealTimeLineUseCase.getRealtimeLinesUseCase(roomID = roomID).collectLatest {result->
                when(result){
                    is ResultState.Loading->{
                        _getRealtimeLinesState.value = GetRealTimeLines(
                            isLoading = true
                        )
                    }
                    is ResultState.Success->{
                        _getRealtimeLinesState.value = GetRealTimeLines(
                            isLoading = false, data = result.data
                        )
                    }
                    is ResultState.Error->{
                        _getRealtimeLinesState.value =GetRealTimeLines(
                            isLoading = false, error = result.message
                        )
                    }
                }

            }
        }

    }

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
