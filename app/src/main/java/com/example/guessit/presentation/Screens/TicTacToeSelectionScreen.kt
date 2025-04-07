package com.example.guessit.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.guessit.presentation.Navigation.TICTACTOEMULTIPLAYEROFFLINE


@Composable
fun TicTacToeSelectionScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        OutlinedButton(onClick = {
            //
            navController.navigate(TICTACTOEMULTIPLAYEROFFLINE)


        }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeMatchingYellow))
            , elevation =ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Offline Multiplayer", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedButton(onClick = {


        }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200))
            , elevation =ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Offline vs AI", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = {


        }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeLightMatching2))
            , elevation =ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Online Friend", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

    }

}