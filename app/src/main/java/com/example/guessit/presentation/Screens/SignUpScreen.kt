package com.example.guessit.presentation.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.presentation.ViewModel.AppViewModel

@Composable
fun SignUpScreen(navController: NavController,viewModel: AppViewModel= hiltViewModel()) {
    Text("SignUpScreen Here")
}