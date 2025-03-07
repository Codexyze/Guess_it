package com.example.guessit.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.R
import com.example.guessit.presentation.Navigation.CREATEROOMSCREEN
import com.example.guessit.presentation.Navigation.JOINSCREEN
import com.example.guessit.presentation.Navigation.PAINTSCREEN
import com.example.guessit.presentation.ViewModel.AppViewModel

@Composable
fun HomeScreen(navController: NavController,viewModel: AppViewModel= hiltViewModel()) {
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

   }

}