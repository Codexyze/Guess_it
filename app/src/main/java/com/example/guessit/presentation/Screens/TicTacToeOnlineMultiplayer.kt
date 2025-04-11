package com.example.guessit.presentation.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.data.dataClasses.TicTacToe.TicTacToeDataClass
import com.example.guessit.presentation.ViewModel.TicTacToeRoomCreateViewModel

@Composable
fun TicTacToeOnlineMultiplayerScreen(
    navController: NavController,
    playerName: String,
    roomID: String,
    viewmodel: TicTacToeRoomCreateViewModel = hiltViewModel()
) {
    val gameState = viewmodel.ticTacToeDataState.collectAsState()
    val updateState = viewmodel.updateTicTacToeDataState.collectAsState()
    val gameData = gameState.value.data

    val board = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") }
    val moveCounter = remember { mutableStateOf(0) }
    val playerTurn = remember { mutableStateOf("X") }
    val winner = remember { mutableStateOf("") }

    val winningCombinations = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
        listOf(0, 4, 8), listOf(2, 4, 6)                  // Diagonals
    )

    LaunchedEffect(Unit) {
        viewmodel.getTTTdataWithID(roomID)
    }

    LaunchedEffect(gameData) {
        gameData?.let {
            board.clear()
            board.addAll(it.board)
            moveCounter.value = it.movesCounter
            playerTurn.value = it.playerTurn
            winner.value = it.winner
        }
    }

    if (gameState.value.isLoading && updateState.value.isLoading) {
        LoadingBar()
        return
    }

    if (gameState.value.error != null && updateState.value.error != null) {
        Text("Server error: ${gameState.value.error}")
        return
    }

    // 👇 Check if it's the local player's turn
    val isMyTurn = remember(gameData, playerTurn.value, playerName) {
        if (playerTurn.value == "X") {
            playerName == gameData?.player1name
        } else {
            playerName == gameData?.player2name
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Game Board
        for (row in 0..2) {
            Row {
                for (col in 0..2) {
                    val index = row * 3 + col
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(2.dp, Color.Black)
                            .clickable(
                                enabled = board[index].isEmpty() &&
                                        winner.value.isEmpty() &&
                                        isMyTurn // ✅ Only allow valid turn
                            ) {
                                board[index] = playerTurn.value
                                moveCounter.value++

                                // Check for winner
                                for (combo in winningCombinations) {
                                    val (a, b, c) = combo
                                    if (
                                        board[a].isNotEmpty() &&
                                        board[a] == board[b] &&
                                        board[a] == board[c]
                                    ) {
                                        winner.value = playerTurn.value
                                    }
                                }

                                // Switch turn
                                playerTurn.value = if (playerTurn.value == "X") "O" else "X"

                                // Update in Firebase
                                val updatedGame = TicTacToeDataClass(
                                    player1LeaderUID = roomID,
                                    player2UID = gameData?.player2UID ?: "",
                                    board = board.toList(),
                                    winner = winner.value,
                                    movesCounter = moveCounter.value,
                                    playerTurn = playerTurn.value,
                                    player1name = gameData?.player1name ?: "Player1",
                                    player2name = gameData?.player2name ?: "Player2"
                                )
                                viewmodel.updateTicTacToeData(updatedGame)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(board[index], fontSize = 48.sp)
                    }
                }
            }
        }

        // Game Status
        Spacer(modifier = Modifier.height(16.dp))
        when {
            winner.value.isNotEmpty() -> Text("🎉 Winner: ${winner.value}", fontSize = 20.sp)
            moveCounter.value == 9 -> Text("🤝 It's a draw!", fontSize = 20.sp)
            else -> Text("⏳ ${playerTurn.value}'s Turn", fontSize = 16.sp)
        }

        // Replay Button
        if (winner.value.isNotEmpty() || moveCounter.value == 9) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // Reset board
                board.clear()
                board.addAll(List(9) { "" })
                moveCounter.value = 0
                playerTurn.value = "X"
                winner.value = ""

                // Push to Firebase
                val resetGame = TicTacToeDataClass(
                    player1LeaderUID = roomID,
                    player2UID = gameData?.player2UID ?: "",
                    board = board.toList(),
                    winner = "",
                    movesCounter = 0,
                    playerTurn = "X",
                    player1name = gameData?.player1name ?: "Player1",
                    player2name = gameData?.player2name ?: "Player2"
                )
                viewmodel.updateTicTacToeData(resetGame)
            }) {
                Text("Replay")
            }
        }
    }
}
