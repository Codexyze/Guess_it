package com.example.guessit.domain.UseCases

import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class UploadLineToRealTimeDataBaseUseCase(private val repository: Repository) {
    suspend fun uploadLineTorealTimeDatabaseUseCase(lines:LiveLine,roomID: String):Flow<ResultState<String>>{
        return repository.uploadLineTorealTimeDatabase(lines,roomID)
    }
}