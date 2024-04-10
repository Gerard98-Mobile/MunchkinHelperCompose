package com.example.munchkinhelpercompose

import android.content.Context
import android.media.SoundPool
import androidx.annotation.RawRes

class SoundManager(context: Context) {

    private companion object {
        const val SMALL_PRIORITY = 0
        const val HIGH_PRIORITY = 1
    }

    enum class Effect(
        @RawRes val resId: Int,
        val priority: Int = SMALL_PRIORITY
    ) {
        LEVEL_UP(R.raw.level_up),
        LEVEL_DOWN(R.raw.level_down),
        DEATH(R.raw.you_lose, HIGH_PRIORITY),
        WIN(R.raw.win_sound, HIGH_PRIORITY),
    }

    private val soundPool = SoundPool.Builder().build()
    private val sounds: MutableMap<Effect, Int> = mutableMapOf()

    init {
        soundPool.apply {
            Effect.values().forEach {
                sounds[it] = this.load(context, it.resId, it.priority)
            }
        }
    }

    fun play(effect: Effect) = sounds[effect]?.let{
        soundPool.play(it, 1f, 1f, effect.priority, 0, 1f)
    }

    fun release() {
        soundPool.release()
    }

}