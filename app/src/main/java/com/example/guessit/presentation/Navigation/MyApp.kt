package com.example.guessit.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guessit.presentation.Screens.HomeScreen
import com.example.guessit.presentation.Screens.SignUpScreen
import com.example.guessit.presentation.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

@Composable
fun MyApp (viewModel: AppViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        val currentUser= FirebaseAuth.getInstance().currentUser?.uid
        if (currentUser != null){
            navController.navigate(HOMESCREEN){
                popUpTo(0)
            }
        }else{
            navController.navigate(SIGNUPSCREEN){
                popUpTo(0)
            }
        }

    }

    NavHost(navController = navController, startDestination =HOMESCREEN ) {
        composable<HOMESCREEN> {
            HomeScreen(navController = navController)
        }
        composable<SIGNUPSCREEN> {
            SignUpScreen(navController = navController)
        }

    }

}