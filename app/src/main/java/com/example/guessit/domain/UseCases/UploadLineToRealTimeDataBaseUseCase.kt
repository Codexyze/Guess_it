package com.example.guessit.domain.UseCases

import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.Repository.UploadLinesRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class UploadLineToRealTimeDataBaseUseCase(private val repository: UploadLinesRepository) {
    suspend fun uploadLineTorealTimeDatabaseUseCase(lines:LiveLine,roomID: String):Flow<ResultState<String>>{
        return repository.uploadLineTorealTimeDatabase(lines,roomID)
    }
}