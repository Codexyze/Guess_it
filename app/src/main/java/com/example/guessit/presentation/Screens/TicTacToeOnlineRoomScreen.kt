package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.guessit.presentation.ViewModel.AppViewModel

@Composable
fun TicTacToeOnlineRoomScreen(viewModel: AppViewModel= hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Create Room For TicTacToeMultiplayer Game")


    }

}