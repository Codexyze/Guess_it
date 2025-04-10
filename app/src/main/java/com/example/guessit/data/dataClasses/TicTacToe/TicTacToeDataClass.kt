package com.example.guessit.data.dataClasses.TicTacToe

data class TicTacToeDataClass(
    val player1 : String = "",
    val player2 : String ="",
    val board : List<String> =listOf("","","","","","","","",""),
    val winner : String="",
    val movesCounter : Int =0,
    val playerTurn : String = "X",
    val player1name: String ="Player1",
    val player2name: String = "Player2"
)
