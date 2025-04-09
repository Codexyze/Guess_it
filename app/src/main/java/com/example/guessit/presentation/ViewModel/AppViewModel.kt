package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.StateHandeling.CreateRoomState
import com.example.guessit.domain.StateHandeling.GetAllMessageFromRoomState
import com.example.guessit.domain.StateHandeling.GetAllPlayerInRoomState
import com.example.guessit.domain.StateHandeling.GetRealTimeLines
import com.example.guessit.domain.StateHandeling.GetWordFromServerState
import com.example.guessit.domain.StateHandeling.JoinRoomState
import com.example.guessit.domain.StateHandeling.ResultState

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

    private val _getWordFromServerState= MutableStateFlow(GetWordFromServerState())
    val getWordFromServerSate = _getWordFromServerState.asStateFlow()
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



}

