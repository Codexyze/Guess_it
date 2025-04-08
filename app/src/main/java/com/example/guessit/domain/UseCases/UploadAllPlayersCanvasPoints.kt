package com.example.guessit.domain.UseCases

import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.domain.Repository.UploadLinesRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class UploadAllPlayersCanvasPoints(private val repository: UploadLinesRepository) {
    suspend fun uploadAllPlayersCanvasPointsUseCase(lineCordinates:Lines,roomID: String):Flow<ResultState<String>>{
        return repository.uploadAllPlayersCanvasPoints(lineCordinates,roomID)
    }
}