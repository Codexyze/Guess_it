package com.example.guessit.presentation.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.presentation.ViewModel.AppViewModel

@Composable
fun PaintScreen(navController: NavController,viewmodel:AppViewModel= hiltViewModel()) {
    val lines = remember { mutableStateListOf<Lines>() }
    Column(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.White).pointerInput(key1 = true){
            detectDragGestures { change, dragAmount ->
                change.consume()
                val line = Lines(
                    start = change.position - dragAmount,
                    end = change.position,
                    strokeWidth = 10.dp,
                    color = Color.Green
                )
                lines.add(line)
            }
        }) {
            lines.forEach {line->
                drawLine(
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth.toPx(),
                    color = line.color
                )

            }

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {

            }) {

            }
            Button(onClick = {

            }) {

            }
            Button(onClick = {

            }) {

            }
            Button(onClick = {

            }) {

            }
        }

    }

}