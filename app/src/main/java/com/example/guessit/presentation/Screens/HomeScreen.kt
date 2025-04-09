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
import com.example.guessit.presentation.Navigation.CREATEROOMSCREEN
import com.example.guessit.presentation.Navigation.JOINSCREEN
import com.example.guessit.presentation.Navigation.PAINTSCREEN
import com.example.guessit.presentation.Navigation.TICTACTOESELECTIONSCREEN

@Composable
fun HomeScreen(navController: NavController) {
   Column(modifier = Modifier.fillMaxSize()) {
       Spacer(modifier = Modifier.fillMaxHeight(0.05f))
     OutlinedButton(onClick = {
       //
         navController.navigate(CREATEROOMSCREEN)

     }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
         colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeMatchingYellow))
     , elevation =ButtonDefaults.buttonElevation(8.dp)
     ) {
         Text("Create Room", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
     }
       Spacer(modifier = Modifier.height(16.dp))


       OutlinedButton(onClick = {
           //
           navController.navigate(JOINSCREEN)


       }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
           colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200))
           , elevation =ButtonDefaults.buttonElevation(8.dp)
       ) {
           Text("Join Room", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
       }

       Spacer(modifier = Modifier.height(16.dp))

       OutlinedButton(onClick = {
           navController.navigate(PAINTSCREEN)

       }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
           colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeLightMatching2))
           , elevation =ButtonDefaults.buttonElevation(8.dp)
       ) {
           Text("Paint", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
       }
       Spacer(modifier = Modifier.height(16.dp))

       OutlinedButton(onClick = {
           navController.navigate(TICTACTOESELECTIONSCREEN)

       }, modifier = Modifier.fillMaxWidth().height(80.dp), shape = RectangleShape,
           colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.ThemeLightMatching1))
           , elevation =ButtonDefaults.buttonElevation(8.dp)
       ) {
           Text("TicTacToe", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
       }

   }

}