package com.example.guessit.data.TicTacToe

data class TicTacToeDataClass(
    val player1 : String,
    val player2 : String,
    val board : List<String>,
    val winner : String,
    val movesCounter : Int,
    val playerTurn : String = "X"
)
