package com.example.guessit.presentation.Screens

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine

@Composable
fun PaintScreen(navController: NavController) {
    val lines = remember { mutableStateListOf<Lines>() }
    val colorvalue= remember { mutableStateOf(0) }
    var colorVariable:Color = Color.DarkGray
    var eraserStrokeWidth: Dp = 10.dp
    Column(modifier = Modifier.fillMaxSize()) {

        Canvas(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.White).pointerInput(key1 = true){
            detectDragGestures { change, dragAmount ->
                change.consume()
                if(colorvalue.value==0){
                    colorVariable = Color.Green
                    eraserStrokeWidth=10.dp
                }else if(colorvalue.value == 1){
                    colorVariable = Color.Red
                    eraserStrokeWidth=10.dp
                }else if(colorvalue.value == 2){
                    colorVariable = Color.Blue
                    eraserStrokeWidth=10.dp

                }else if(colorvalue.value == 3){
                    colorVariable = Color.Yellow
                    eraserStrokeWidth=10.dp
                }else{
                    colorVariable =Color.White
                    eraserStrokeWidth = 50.dp
                }
                val line = Lines(
                    start = change.position - dragAmount,
                    end = change.position,
                    strokeWidth =eraserStrokeWidth,
                    color = colorVariable
                )

                lines.add(line)
                Log.d("LINES",lines.toString())
            }
        }) {
            lines.forEach {line->
                Log.d("LINES",line.start.toString())
                Log.d("LINES",line.end.toString())
                Log.d("LINES",line.color.toString())
                Log.d("LINES",line.strokeWidth.toString())
                drawLine(
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth.toPx(),
                    color =line.color

                )


            }

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                colorvalue.value = 1

            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {

            }
            Button(onClick = {
                colorvalue.value = 2
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {

            }
            Button(onClick = {
                colorvalue.value = 3
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)) {

            }
            Button(onClick = {
                colorvalue.value = 4

            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                Text("Erase")
            }

        }

    }

}