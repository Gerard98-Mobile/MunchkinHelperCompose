package com.example.munchkinhelpercompose

import android.content.Context
import android.media.SoundPool
import androidx.annotation.RawRes

class SoundManager(context: Context) {

    enum class Effect(
        @RawRes val resId: Int
    ) {
        LEVEL_UP(R.raw.level_up),
        LEVEL_DOWN(R.raw.level_down),
        DEATH(R.raw.you_lose),
        WIN(R.raw.win_sound),
    }

    private val soundPool = SoundPool.Builder().build()
    private val sounds: MutableMap<Effect, Int> = mutableMapOf()

    init {
        soundPool.apply {
            Effect.values().forEach {
                sounds[it] = this.load(context, it.resId, 0)
            }
        }
    }

    fun play(effect: Effect) = sounds[effect]?.let{
        soundPool.play(it, 1f, 1f, 0, 0, 1f)
    }

    fun release() {
        soundPool.release()
    }

}