package com.example.guessit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.guessit.presentation.Navigation.MyApp
import com.example.guessit.presentation.Screens.TicTacToeOffline
import com.example.guessit.ui.theme.GuessItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            GuessItTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  Box(modifier = Modifier.padding(innerPadding)){
                      MyApp()
                      //TicTacToeOffline()

                  }
                }
            }
        }
    }
}
