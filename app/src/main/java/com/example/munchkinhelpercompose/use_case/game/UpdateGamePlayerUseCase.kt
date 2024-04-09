package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.model.Game
import com.example.munchkinhelpercompose.model.Player
import javax.inject.Inject


class UpdateGamePlayerUseCase @Inject constructor(
    private val updateGame: UpdateGameUseCase,
    private val updatePlayer: UpdatePlayerUseCase
) {
    suspend operator fun invoke(player: Player, update: Player.() -> Player, game: Game): Game? {
        val updated = updatePlayer(player, update) ?: return null

        val players = game.players.toMutableList()
        val changedPlayerIdx = game.players.indexOf(player)
        players[changedPlayerIdx] = updated

        val result = game.copy(players = players.toSet())
        updateGame.invoke(result)
        return result
    }
}