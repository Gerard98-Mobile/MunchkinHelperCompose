package com.example.munchkinhelpercompose.model

data class Player(
    val name: String,
    val level: Int = 1,
    val power: Int = 0
) {

    val powerWithLevel : Int
        get() = level + power

}