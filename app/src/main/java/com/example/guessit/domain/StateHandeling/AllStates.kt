package com.example.guessit.domain.StateHandeling

import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.data.dataClasses.Player

sealed class ResultState<out T>{
    object Loading : ResultState<Nothing>()
    data class Success<T>(val data : T) : ResultState<T>()
    data class Error(val message : String) : ResultState<Nothing>()
}

data class SignUpState(val isLoading:Boolean = false,
        val data:String? = null,
        val error:String? = null)

data class LoginState(val isLoading:Boolean = false,
                       val data:String? = null,
                       val error:String? = null)

data class GetWordFromServerState(
    val isLoading:Boolean =false ,
    val data:List<String> = emptyList(),
     val error:String? = null
)

data class CreateRoomState(
    val isLoading:Boolean  = false ,
    val data:String? = null,
    val error:String ? = null
)

data class JoinRoomState(
    val isLoading: Boolean = false,
    val data: String? =null,
    val error:String? =null
)

data class GetAllPlayerInRoomState(
    val isLoading: Boolean = false,
    val data:List<Player> = emptyList(),
    val error:String? =null

)

data class UploadLineCordinatesState(
    val isLoading: Boolean = false,
    val data:String ? = null,
    val error: String? =null
)

data class UploadLinesToRealTimeDataBaseState(
    val isLoading: Boolean = false,
    val data:String ? = null,
    val error: String? =null
)

data class GetRealTimeLines(
    val isLoading: Boolean = false,
    val data:LiveLine ? = null,
    val error: String? =null
)