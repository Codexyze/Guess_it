package com.example.guessit.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.guessit.presentation.Screens.CreateOrJoinSelectionScreen
import com.example.guessit.presentation.Screens.CreateRoomScreen
import com.example.guessit.presentation.Screens.CreateTicTacToeRoom
import com.example.guessit.presentation.Screens.HomeScreen
import com.example.guessit.presentation.Screens.JoinRoomScreen
import com.example.guessit.presentation.Screens.LoginScreen
import com.example.guessit.presentation.Screens.MessageScreen
import com.example.guessit.presentation.Screens.PaintScreen
import com.example.guessit.presentation.Screens.PlayScreen
import com.example.guessit.presentation.Screens.SignUpScreen
import com.example.guessit.presentation.Screens.TTTJoinScreen
import com.example.guessit.presentation.Screens.TicTacToeCreateRoomScreen
import com.example.guessit.presentation.Screens.TicTacToeOffline
import com.example.guessit.presentation.Screens.TicTacToeOnlineMultiplayerScreen
import com.example.guessit.presentation.Screens.TicTacToeSelectionScreen
import com.google.firebase.auth.FirebaseAuth
@Preview(showBackground = true)
@Composable
fun MyApp () {
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
        composable<LOGINSCREEN> {
           LoginScreen(navController = navController)
        }
        composable<PAINTSCREEN> {
            PaintScreen(navController = navController)
        }
        composable<CREATEROOMSCREEN> {
            CreateRoomScreen(navController = navController)
        }
        composable<PLAYSCREEN> {backstackentry->
            val data:PLAYSCREEN = backstackentry.toRoute()
            PlayScreen(navController = navController, roomID = data.roomID, name = data.name)
        }
        composable<JOINSCREEN>{
            JoinRoomScreen(navController = navController)
        }
        composable<MESSAGESCREEN> {args->
            val argsdata:MESSAGESCREEN = args.toRoute()
            MessageScreen(roomID = argsdata.roomID, name = argsdata.name)
        }
        composable<TICTACTOESELECTIONSCREEN> {
            TicTacToeSelectionScreen(navController = navController)
        }
        composable<TICTACTOEMULTIPLAYEROFFLINE> {
            TicTacToeOffline()
        }
        composable<TICTACTOECREATEROOMSCREEN>{
            CreateTicTacToeRoom(navController = navController)
        }
        composable<CREATEORJOINTICTACTOESCREEN>{
            CreateOrJoinSelectionScreen(navController = navController)
        }
        composable<TICTACTOEONLINEMULTIPLAYERSCREEN>{
            TicTacToeOnlineMultiplayerScreen(navController = navController)
        }
        composable<TICTACTTOEJOINROOMSCREEN> {
            TTTJoinScreen(navController = navController)
        }

    }

}