package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.model.Player


class UpdatePlayerUseCase {
    companion object {
        private const val MIN_LEVEL_POSSIBLE = 1
        private const val MIN_POWER_POSSIBLE = 0
    }

    operator fun invoke(player: Player, update: Player.() -> Player): Player? {
        val updated = update(player)
        if (updated.level < MIN_LEVEL_POSSIBLE || updated.power < MIN_POWER_POSSIBLE) return null
        return updated
    }
}