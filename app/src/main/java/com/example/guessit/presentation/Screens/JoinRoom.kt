package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.presentation.ViewModel.AppViewModel
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun JoinRoomScreen(navController: NavController,viewmodel:AppViewModel = hiltViewModel()) {
    val joinRoomState = viewmodel.joinRoomState
    val userID = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("JoinScreen here")
       OutlinedTextField(value = userID.value , onValueChange = {
           userID.value = it
       }, placeholder = {
           Text("UserID")
       })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = userID.value , onValueChange = {
            userID.value = it
        }, placeholder = {
            Text("Name")
        })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if(userID.value.isNullOrEmpty() && userName.value.isNullOrEmpty() && userName.value.length>=5){
                val player =Player(
                    userID = userID.value,
                    userName = userName.value
                )

            }else{
                FancyToast.makeText(context,"Enter All Fields",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,false).show()

            }

        }) {
            Text("JOIN Room")
        }

    }



}
// val userID:String,
//    val score:Int = 0,
//    val totalGuess:Int =0,
//    val postion:Int =0,
//    val userName:String ="Player NoName"