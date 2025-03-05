package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.presentation.ViewModel.AppViewModel

@Composable
fun PlayScreen(navController: NavController,viewmodel:AppViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("PlayScreen here")
    }


}