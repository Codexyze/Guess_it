package com.example.guessit.presentation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentPaste
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.presentation.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun CreateRoomScreen(viewmodel:AppViewModel = hiltViewModel(),navController: NavController) {
    val clipbordManager = LocalClipboardManager.current
    val userID = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val createRoomState = viewmodel.createRoomState.collectAsState()
    //dont forget to call the
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        userID.value = FirebaseAuth.getInstance().currentUser?.uid.toString()
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Copy The Code", fontSize = 28.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(userID.value)
            Icon(
                imageVector = Icons.Outlined.ContentPaste, contentDescription = "ClipBoard", modifier =
                    Modifier.clickable {
                        clipbordManager.setText(AnnotatedString(userID.value))
                        FancyToast.makeText(context,"Copied To ClipBoard !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show()
                    }
            )
        }
        OutlinedTextField(value =userName.value , onValueChange = {
            userName.value = it
        } , modifier = Modifier.fillMaxWidth(0.85f), placeholder = {
            Text("Player Name")
        })
        Button(onClick = {
        // call fun from backend
            FancyToast.makeText(context,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show()
        }) {
            Text("Click t craete Room")
        }

    }



}