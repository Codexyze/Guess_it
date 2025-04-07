package com.example.guessit.domain.UseCases

import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

class UploadAllPlayersCanvasPoints(private val repository: Repository) {
    suspend fun uploadAllPlayersCanvasPointsUseCase(lineCordinates:Lines,roomID: String):Flow<ResultState<String>>{
        return repository.uploadAllPlayersCanvasPoints(lineCordinates,roomID)
    }
}