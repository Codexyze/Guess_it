package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentPaste
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessit.R
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun TicTacToeCreateRoomScreen() {
    val clipboardManager = LocalClipboardManager.current
    val userID = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        userID.value = FirebaseAuth.getInstance().currentUser?.uid.toString()
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Copy The Code",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = userID.value,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = {
                    clipboardManager.setText(AnnotatedString(userID.value))
                    FancyToast.makeText(
                        context,
                        "Copied To Clipboard!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.ContentPaste,
                        contentDescription = "Copy to Clipboard",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Player Name") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.ThemeMatchingYellow))
            ) {
                Text(text = "Create Room", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }


        }
    }