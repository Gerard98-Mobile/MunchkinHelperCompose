package gerard.example.munchkinhelper.model

data class Game(
    val players: Set<Player>,
    val startTime: Long = System.currentTimeMillis()
) {
    fun reset(): Game = this.copy(
        players = players.map { it.reset() }.toSet(),
        startTime = System.currentTimeMillis()
    )
}