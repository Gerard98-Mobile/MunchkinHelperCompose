package com.example.munchkinhelpercompose.model

data class Game(
    val players: Set<Player>,
    val startTime: Long = System.currentTimeMillis()
)