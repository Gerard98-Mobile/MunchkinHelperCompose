package com.example.munchkinhelpercompose.use_case.game

import com.example.munchkinhelpercompose.SoundManager
import com.example.munchkinhelpercompose.model.Player
import com.example.munchkinhelpercompose.model.Settings
import javax.inject.Inject

class PlayPlayerChangeSoundEffectUseCase @Inject constructor(
    private val soundManager: SoundManager,
) {
    operator fun invoke(player: Player, changedPlayer: Player, settings: Settings?) {
        if (settings?.sound == false) return
        when {
            changedPlayer.level == 10 -> soundManager.play(SoundManager.Effect.WIN)
            player.deaths < changedPlayer.deaths -> soundManager.play(SoundManager.Effect.DEATH)
            player.level < changedPlayer.level -> soundManager.play(SoundManager.Effect.LEVEL_UP)
            player.level > changedPlayer.level -> soundManager.play(SoundManager.Effect.LEVEL_DOWN)
        }
    }
}