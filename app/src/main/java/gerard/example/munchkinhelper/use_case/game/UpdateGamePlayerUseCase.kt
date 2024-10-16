package gerard.example.munchkinhelper.use_case.game

import gerard.example.munchkinhelper.model.Game
import gerard.example.munchkinhelper.model.Player
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