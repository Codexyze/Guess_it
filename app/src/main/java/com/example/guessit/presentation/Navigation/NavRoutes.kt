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
