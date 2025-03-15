package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.presentation.Navigation.PLAYSCREEN
import com.example.guessit.presentation.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun JoinRoomScreen(navController: NavController,viewmodel:AppViewModel = hiltViewModel()) {
    val currentuser = FirebaseAuth.getInstance().currentUser?.uid
    val joinRoomState = viewmodel.joinRoomState.collectAsState()
    val roomID = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val context = LocalContext.current
    if(joinRoomState.value.isLoading){
        LoadingBar()
    }else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("JoinScreen here")
            OutlinedTextField(value = roomID.value, onValueChange = {
                roomID.value = it
            }, placeholder = {
                Text("Room ID")
            })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = userName.value, onValueChange = {
                userName.value = it
            }, placeholder = {
                Text("Name")
            })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (roomID.value.isNotEmpty() && userName.value.isNotEmpty() && userName.value.length >= 5) {
                    val player = Player(
                        userID = currentuser.toString(),
                        userName = userName.value
                    )
                    try {
                        viewmodel.joinRoomUsingUserID(roomID = roomID.value.toString(), player = player )
                        navController.navigate(PLAYSCREEN(roomID = roomID.value, name = userName.value)){
                            popUpTo(0)
                        }
                    }catch (e:Exception){
                        FancyToast.makeText(
                            context, "Error",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR, false
                        ).show()
                    }

                } else {
                    FancyToast.makeText(
                        context, "Enter All Fields",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR, false
                    ).show()

                }

            }) {
                Text("JOIN Room")
            }

        }

    }

}
