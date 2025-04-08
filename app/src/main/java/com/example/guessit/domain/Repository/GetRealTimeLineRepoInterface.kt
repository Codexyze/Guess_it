package com.example.guessit.domain.Repository

import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface GetRealTimeLinesRepository {
    suspend fun getRealtimeLines(roomID: String):Flow<ResultState<LiveLine>>//
}