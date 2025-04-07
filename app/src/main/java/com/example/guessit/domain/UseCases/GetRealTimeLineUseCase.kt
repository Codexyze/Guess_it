package com.example.guessit.domain.UseCases

import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class GetRealTimeLineUseCase(private val repository: Repository) {
    suspend fun getRealtimeLinesUseCase(roomID: String):Flow<ResultState<LiveLine>>{
        return repository.getRealtimeLines(roomID)
    }
}