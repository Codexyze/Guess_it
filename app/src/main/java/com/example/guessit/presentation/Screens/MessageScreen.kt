package com.example.guessit.presentation.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.presentation.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun MessageScreen(roomID:String,name:String,viewmodel:AppViewModel = hiltViewModel()) {
    val message = remember { mutableStateOf("") }
    val context = LocalContext.current
    val userID= FirebaseAuth.getInstance().currentUser?.uid
    val sendMessageToRoomMemberState = viewmodel.sendMessageToRoomMembersState.collectAsState()
    val getAllMessageFromRoomState = viewmodel.getAllMessageFromRoomState.collectAsState()
    LaunchedEffect(Unit) {
        viewmodel.getAllMessageFromRoom(roomID = roomID)

    }
    if(sendMessageToRoomMemberState.value.isLoading&& getAllMessageFromRoomState.value.isLoading ){
        LoadingBar()
    }else if(sendMessageToRoomMemberState.value.error !=null && getAllMessageFromRoomState.value.error !=null){
        Toast.makeText(context, "Error in Loading Messages", Toast.LENGTH_SHORT).show()
    }else{
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Chats")
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(value = message.value, onValueChange = {
                    message.value = it
                }, placeholder = {
                    Text("Message")
                }, modifier = Modifier.fillMaxWidth(0.70f))
                Button(
                    onClick = {
                        val message = Message(
                            message = message.value,
                            postLink = "Not now",
                            userID = userID.toString()
                        )
                        viewmodel.sendMessageToRoomMembers(roomID = roomID, message = message)

                    }, modifier = Modifier.fillMaxWidth(0.30f)
                ) {
                    Text("Send")
                }

            }
            if(getAllMessageFromRoomState.value.data != null){
                getAllMessageFromRoomState.value.data.forEach { message ->
                    Text(message.message)
                }
            }else{
                Text("No message")
            }


        }
    }


}