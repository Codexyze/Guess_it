package com.example.guessit.data.PainterDataClass

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.guessit.R

data class Lines(
    val start:Offset,
    val end:Offset,
    val color:Color= Color.Blue,
    val strokeWidth:Dp =10.dp
)
