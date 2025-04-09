package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guessit.R


@Composable
fun CreateOrJoinSelectionScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(onClick = {
            //

        }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeMatchingYellow))
            , elevation =ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Create Room", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedButton(onClick = {
            //


        }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200))
            , elevation =ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Join Room", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

    }
}