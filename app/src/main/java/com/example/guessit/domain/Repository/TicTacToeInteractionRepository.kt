package com.example.guessit.domain.Repository

import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface TicTacToeInteractionRepository {

    suspend fun getTicTacToeData(roomID: String):Flow<ResultState<TicTacToeDataClass>>

    suspend fun updateTicTacToeData(ticTacToeData: TicTacToeDataClass):Flow<ResultState<String>>

    suspend fun joinTTTRoomWithID(roomID:String,playerName: String):Flow<ResultState<String>>

}