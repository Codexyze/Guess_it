package com.example.guessit.presentation.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.play.integrity.internal.b

@Preview(showBackground = true)
@Composable
fun TicTacPratice() {
    val board = remember {  mutableStateListOf("","","","","","","","","" )}
    val turn = remember { mutableStateOf("X") }
    val winner = remember { mutableStateOf<String>("") }
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
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        for(row in 0..2) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                for (col in 0..2) {
                    val index = row * 3 + col
                    Box(modifier = Modifier.size(100.dp).border(2.dp, Color.Black).clickable(
                        enabled = if(winner.value.isNullOrBlank() && board[index].isEmpty()){
                             true

                        }else{
                            false
                        }
                    ){
                           board[index] =  turn.value


                        for(combination in winningCombinations){
                            val a = combination[0]
                            val b = combination[1]
                            val c = combination[2]

                            if(board[a].isNotEmpty() && board[a]== board[b] && board[a]== board[c]){
                                winner.value = turn.value
                            }
                        }


                        if(turn.value == "X"){
                            turn.value = "O"
                        }else{
                            turn.value = "X"
                        }


                    }) {
                       Text(board[index])

                    }


                }
            }

        }
        if(winner.value != ""){
            Text("Winner is ${winner.value}")
        }

    }
}