package com.example.guessit.presentation.Navigation

import kotlinx.serialization.Serializable

@Serializable
object HOMESCREEN

@Serializable
object SIGNUPSCREEN

@Serializable
object LOGINSCREEN

@Serializable
object PAINTSCREEN

@Serializable
object CREATEROOMSCREEN

@Serializable
data class PLAYSCREEN(
    val roomID:String,
    val name:String
)

@Serializable
object JOINSCREEN

@Serializable
data class MESSAGESCREEN(
    val roomID:String,val name:String
)

@Serializable
object TICTACTOESELECTIONSCREEN

@Serializable
object TICTACTOEMULTIPLAYEROFFLINE

@Serializable
object TICTACTOECREATEROOMSCREEN

@Serializable
object CREATEORJOINTICTACTOESCREEN


@Serializable
data class TICTACTOEONLINEMULTIPLAYERSCREEN(val playerName: String, val roomID: String)

@Serializable
object TICTACTTOEJOINROOMSCREEN