package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.R
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.presentation.Navigation.PLAYSCREEN
import com.example.guessit.presentation.Navigation.TICTACTOEONLINEMULTIPLAYERSCREEN
import com.example.guessit.presentation.ViewModel.TicTacToeRoomCreateViewModel
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun TTTJoinScreen(viewmodel:TicTacToeRoomCreateViewModel= hiltViewModel(),navController: NavController) {
    val roomID = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val joinState = viewmodel.joinTTTRoomState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(joinState.value) {
        joinState.value.data?.let {
            navController.navigate(TICTACTOEONLINEMULTIPLAYERSCREEN)
        }
    }

    if(joinState.value.isLoading){
        LoadingBar()
    }else if(joinState.value.error != null){
        //
    }else{
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Join a Room",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = roomID.value,
                onValueChange = { roomID.value = it },
                label = { Text("Room ID") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if(roomID.value.isNotEmpty() && userName.value.length >= 5 ){
                       viewmodel.joinTicTacToeRoom(roomID = roomID.value , playerName = userName.value)
                    }else{
                        FancyToast.makeText(
                           context , "Enter all fields",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR, false
                        ).show()
                    }


                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeMatchingYellow))
            ) {
                Text(text = "JOIN ROOM", fontWeight = FontWeight.Bold)
            }

        }
    }
}