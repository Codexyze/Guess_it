package com.example.guessit.presentation.Screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.presentation.ViewModel.AppViewModel
import kotlinx.coroutines.delay


@Composable
fun PlayScreen(navController: NavController, viewmodel: AppViewModel = hiltViewModel(),roomID:String) {

    val context = LocalContext.current
    val getWordsFromServerState = viewmodel.getWordFromServerSate.collectAsState()
    val getAllPlayerInRoomState = viewmodel.getAllPlayersFromRoomState.collectAsState()
    val index = remember { mutableStateOf(0) }
    val lines = remember { mutableStateListOf<Lines>() }
    val colorvalue = remember { mutableStateOf(0) }
    var colorVariable: Color = Color.DarkGray
    var eraserStrokeWidth: Dp = 10.dp
    var listFromServer:List<Player> = remember { mutableStateListOf<Player>() }

    LaunchedEffect(Unit) {
        viewmodel.getWordFromServer()
        listFromServer = getAllPlayerInRoomState.value.data
        viewmodel.getAllPlayersFromRoom(roomID = roomID)
        while (true){
            delay(5000)
            index.value = index.value + 1
        }

    }

    if (getWordsFromServerState.value.isLoading && getAllPlayerInRoomState.value.isLoading ) {
        LoadingBar()
    } else if (getWordsFromServerState.value.data != null && getAllPlayerInRoomState.value.data.isNotEmpty()) {
        if (viewmodel.getAllPlayersFromRoomState.collectAsState().value.data !=null){
            Log.d("ALLPLAYERS",viewmodel.getAllPlayersFromRoomState.collectAsState().value.data.toString())
        }
        val words = getWordsFromServerState.value.data ?: emptyList()

        if (words.isNotEmpty()) {
            index.value = index.value.coerceIn(0, words.size - 1) // Ensure index is valid
        }

        Column(modifier = Modifier.fillMaxSize()) {

            if (words.isNotEmpty()) {
                Text(
                    text = words[index.value],
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                getAllPlayerInRoomState.value.data.forEach {
                    if (it.isLeader == true){
                        Text("Leader: ${it.userName}")
                    }else{
                        Text("Name : ${it.userName}")//this printed
                    }

                }

            } else {
                Text(
                    text = "No words available",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.White)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            colorVariable = when (colorvalue.value) {
                                0 -> Color.Green
                                1 -> Color.Red
                                2 -> Color.Blue
                                3 -> Color.Yellow
                                else -> Color.White
                            }
                            eraserStrokeWidth = if (colorvalue.value == 4) 50.dp else 10.dp

                            val line = Lines(
                                start = change.position - dragAmount,
                                end = change.position,
                                strokeWidth = eraserStrokeWidth,
                                color = colorVariable
                            )
                            lines.add(line)
                        }
                    }) {
                lines.forEach { line ->
                    drawLine(
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth.toPx(),
                        color = line.color
                    )
                    //modify data
                    val data = Lines(
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth,
                        color = line.color
                    )
                    viewmodel.uploadLiveLineCordinates(
                        lineCordinates = data, roomID = roomID
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { colorvalue.value = 1 }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {}
                Button(onClick = { colorvalue.value = 2 }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {}
                Button(onClick = { colorvalue.value = 3 }, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)) {}
                Button(onClick = { colorvalue.value = 4 }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                    Text("Erase")
                }
            }
        }

    } else {
        Text("Error Screen")
    }
}
