package com.example.guessit.domain.Repository

import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow

interface UploadLinesRepository {
    suspend fun uploadAllPlayersCanvasPoints(lineCordinates:Lines,roomID: String):Flow<ResultState<String>>//
    suspend fun uploadLineTorealTimeDatabase(lines:LiveLine,roomID: String):Flow<ResultState<String>>//
}