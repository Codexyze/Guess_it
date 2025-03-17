package com.example.guessit.presentation.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.presentation.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
//
//@Composable
//fun MessageScreen(roomID:String,name:String,viewmodel:AppViewModel = hiltViewModel()) {
//    val message = remember { mutableStateOf("") }
//    val context = LocalContext.current
//    val userID= FirebaseAuth.getInstance().currentUser?.uid
//    val sendMessageToRoomMemberState = viewmodel.sendMessageToRoomMembersState.collectAsState()
//    val getAllMessageFromRoomState = viewmodel.getAllMessageFromRoomState.collectAsState()
//    LaunchedEffect(Unit) {
//        viewmodel.getAllMessageFromRoom(roomID = roomID)
//
//    }
//    if(sendMessageToRoomMemberState.value.isLoading&& getAllMessageFromRoomState.value.isLoading ){
//        LoadingBar()
//    }else if(sendMessageToRoomMemberState.value.error !=null && getAllMessageFromRoomState.value.error !=null){
//        Toast.makeText(context, "Error in Loading Messages", Toast.LENGTH_SHORT).show()
//    }else{
//        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//
//            Text("Chats")
//            Row(modifier = Modifier.fillMaxWidth()) {
//                OutlinedTextField(value = message.value, onValueChange = {
//                    message.value = it
//                }, placeholder = {
//                    Text("Message")
//                }, modifier = Modifier.fillMaxWidth(0.70f))
//                Button(
//                    onClick = {
//                        val message = Message(
//                            message = message.value,
//                            postLink = "Not now",
//                            userID = userID.toString()
//                        )
//                        viewmodel.sendMessageToRoomMembers(roomID = roomID, message = message)
//
//                    }, modifier = Modifier.fillMaxWidth(0.30f)
//                ) {
//                    Text("Send")
//                }
//
//            }
//            if(getAllMessageFromRoomState.value.data != null){
//                getAllMessageFromRoomState.value.data.forEach { message ->
//                    Text(message.message)
//                }
//            }else{
//                Text("No message")
//            }
//
//
//        }
//    }
//
//
//}
@Composable
fun MessageScreen(roomID: String, name: String, viewmodel: AppViewModel = hiltViewModel()) {
    val message = remember { mutableStateOf("") }
    val context = LocalContext.current
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    val sendMessageToRoomMemberState = viewmodel.sendMessageToRoomMembersState.collectAsState()
    val getAllMessageFromRoomState = viewmodel.getAllMessageFromRoomState.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.getAllMessageFromRoom(roomID = roomID)
    }

    if (sendMessageToRoomMemberState.value.isLoading && getAllMessageFromRoomState.value.isLoading) {
        LoadingBar()
    } else if (sendMessageToRoomMemberState.value.error != null && getAllMessageFromRoomState.value.error != null) {
        Toast.makeText(context, "Error in Loading Messages", Toast.LENGTH_SHORT).show()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Chats",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (getAllMessageFromRoomState.value.data.isNullOrEmpty()) {
                    item {
                        Text(
                            "No messages yet",
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                } else {
                    items(getAllMessageFromRoomState.value.data) { message ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = if (message.userID == userID) Arrangement.End else Arrangement.Start
                        ) {
                            if (message.userID == userID) {
                                Text(
                                    text = message.message,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .background(Color.Green, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp),
                                    fontSize = 16.sp
                                )
                            } else {
                                Text(
                                    text = message.message,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .background(Color.Yellow, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp),
                                    fontSize = 16.sp
                                )
                            }

                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = message.value,
                    onValueChange = { message.value = it },
                    placeholder = { Text("Type a message...", color = Color.Black) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50.dp)
                )
                IconButton(onClick = {
                    val msg = Message(
                        message = message.value,
                        postLink = "Not now",
                        userID = userID.toString()
                    )
                    viewmodel.sendMessageToRoomMembers(roomID = roomID, message = msg)
                    message.value = ""
                }) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                }
            }
        }
    }
}