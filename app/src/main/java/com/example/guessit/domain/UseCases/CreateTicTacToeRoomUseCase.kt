package com.example.guessit.domain.UseCases

import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.domain.Repository.TicTacToeRoomCreateRepository
import com.example.guessit.domain.StateHandeling.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CreateTicTacToeRoomUseCase @Inject constructor(private val repository: TicTacToeRoomCreateRepository) {
    suspend fun ticTacToeRoomCreateUseCase(playerName: String): Flow<ResultState<String>> {
        return repository.ticTacToeCreateRoom(playerName = playerName)
    }
}