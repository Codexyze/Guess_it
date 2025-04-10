package com.example.guessit.presentation.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TicTacToeOnlineMultiplayerScreen(navController: NavController) {
    val board = remember { mutableStateListOf("","","","","","","","","") }
    val winnerplayer = remember { mutableStateOf<String>("") }
    val playerTurn = remember { mutableStateOf("X") }
    val movesCounter = remember { mutableStateOf(0) }
    val winningCombinations = listOf(
        listOf(0, 1, 2), // Top Row
        listOf(3, 4, 5), // Middle Row
        listOf(6, 7, 8), // Bottom Row
        listOf(0, 3, 6), // Left Column
        listOf(1, 4, 7), // Middle Column
        listOf(2, 5, 8), // Right Column
        listOf(0, 4, 8), // Diagonal (Top-Left to Bottom-Right)
        listOf(2, 4, 6)  // Diagonal (Top-Right to Bottom-Left)
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        for (row in 0..2){
            Row {
                for (col in 0..2) {
                    val index = row *3 + col
                    Box(modifier = Modifier.size(100.dp).border(2.dp, Color.Black).clickable(
                        enabled = if(board[index].isEmpty() && winnerplayer.value == ""){
                            true
                        } else{
                            false
                        }
                    ){
                        movesCounter.value++
                        board[index]= playerTurn.value
                        val currentPlayer = playerTurn.value
                        for (combo in winningCombinations){
                            val a = combo[0]
                            val b = combo[1]
                            val c = combo[2]
                            if(board[a].isNotEmpty() && board[a]==board[b] && board[a] == board[c]){
                                winnerplayer.value = currentPlayer
                            }
                        }

                        if(playerTurn.value == "X"){
                            playerTurn.value = "O"
                        }else{
                            playerTurn.value = "X"
                        }

                    }, contentAlignment = Alignment.Center) {
                        Text(board[index], fontSize = 48.sp)


                    }

                }
            }
        }
        if(winnerplayer.value != ""){
            Text("The Winner is Player : ${winnerplayer.value}")
        }else if (movesCounter.value ==9 && winnerplayer.value == ""){
            Text("Looks Like a Draw")
        }

    }

}