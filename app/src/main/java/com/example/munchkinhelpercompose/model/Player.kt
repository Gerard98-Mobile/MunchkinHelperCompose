package com.example.munchkinhelpercompose.model

data class Player(
    val name: String,
    val level: Int = STARTING_LEVEL,
    val power: Int = STARTING_POWER,
    val deaths: Int = STARTING_DEATHS
) {

    companion object {
        const val MAX_PLAYER_NAME_LENGTH = 20
        const val MIN_PLAYER_NAME_LENGTH = 1
        private const val STARTING_LEVEL = 1
        private const val STARTING_POWER = 0
        private const val STARTING_DEATHS = 0
    }

    val powerWithLevel : Int
        get() = level + power

    fun kill(): Player = this.copy(
        deaths = deaths + 1,
        level = STARTING_LEVEL,
        power = STARTING_POWER,
    )

    fun reset(): Player = this.copy(
        deaths = STARTING_DEATHS,
        level = STARTING_LEVEL,
        power = STARTING_POWER,
    )
}