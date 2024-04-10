package com.example.munchkinhelpercompose.model

data class Player(
    val name: String,
    val level: Int = STARTING_LEVEL,
    val power: Int = STARTING_POWER,
    val deaths: Int = STARTING_DEATHS
) {

    private companion object {
        const val STARTING_LEVEL = 1
        const val STARTING_POWER = 0
        const val STARTING_DEATHS = 0
    }

    val powerWithLevel : Int
        get() = level + power

    fun kill(): Player = this.copy(
        deaths = deaths + 1,
        level = STARTING_LEVEL,
        power = STARTING_POWER,
    )
}