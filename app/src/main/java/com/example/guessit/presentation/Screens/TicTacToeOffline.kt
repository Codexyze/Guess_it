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
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun TicTacToeOffline() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentValue = remember { mutableStateOf("O") } // Keeps track of turn
        val currentPlayerTurn = remember { mutableStateOf(0) } // Not used, but kept as per your logic

        // Mutable list to store board values
        val saveDataInList = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") }

        saveDataInList.forEach {
            Text(it) // Debugging - Displays all values
        }

        val currentPlayer = remember { mutableStateOf(0) } // Not used, but kept as per your logic

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (i in 0..2) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .border(width = 3.dp, color = Color.Black)
                        .clickable {
                            if (saveDataInList[i] != "X" && saveDataInList[i] != "O") { // Prevent overwriting
                                if (currentValue.value == "O") {
                                    saveDataInList[i] = "O"
                                    currentValue.value = "X" // Switch turn
                                    if(currentPlayer.value==1){
                                        currentPlayer.value =0
                                    }else{
                                        currentPlayer.value =1
                                    }
                                } else {
                                    saveDataInList[i] = "X"
                                    currentValue.value = "O" // Switch turn
                                    if(currentPlayer.value==1){
                                        currentPlayer.value =0
                                    }else{
                                        currentPlayer.value =1
                                    }
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(saveDataInList[i], fontSize = 32.sp)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (i in 3..5) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .border(width = 3.dp, color = Color.Black)
                        .clickable {
                            if (saveDataInList[i] != "X" && saveDataInList[i] != "O") {
                                if (currentValue.value == "O") {
                                    saveDataInList[i] = "O"
                                    currentValue.value = "X"
                                } else {
                                    saveDataInList[i] = "X"
                                    currentValue.value = "O"
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(saveDataInList[i], fontSize = 32.sp)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (i in 6..8) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .border(width = 3.dp, color = Color.Black)
                        .clickable {
                            if (saveDataInList[i] != "X" && saveDataInList[i] != "O") {
                                if (currentValue.value == "O") {
                                    saveDataInList[i] = "O"
                                    currentValue.value = "X"
                                } else {
                                    saveDataInList[i] = "X"
                                    currentValue.value = "O"
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(saveDataInList[i], fontSize = 32.sp)
                }
            }
        }
        if (saveDataInList[0] == saveDataInList[1] && saveDataInList[0]== saveDataInList[2]){
            Text("Player won ${currentPlayer.value}")
        }else if(saveDataInList[0] == saveDataInList[3] && saveDataInList[0]== saveDataInList[6]){
            Text("Player won ${currentPlayer.value}")

        }else if(saveDataInList[0] == saveDataInList[4] && saveDataInList[0]== saveDataInList[8]){
            Text("Player won ${currentPlayer.value}")

        }else if(saveDataInList[2] == saveDataInList[4] && saveDataInList[2]== saveDataInList[6]){
            Text("Player won ${currentPlayer.value}")

        }else if(saveDataInList[1] == saveDataInList[4] && saveDataInList[1]== saveDataInList[7]){
            Text("Player won ${currentPlayer.value}")

        }else if(saveDataInList[2] == saveDataInList[5] && saveDataInList[2]== saveDataInList[8]){
            Text("Player won ${currentPlayer.value}")

        }else if(saveDataInList[3] == saveDataInList[4] && saveDataInList[3]== saveDataInList[5]){
            Text("Player won ${currentPlayer.value}")

        }else if(saveDataInList[6] == saveDataInList[7] && saveDataInList[6]== saveDataInList[8]){
            Text("Player won ${currentPlayer.value}")

        }
    }
}

