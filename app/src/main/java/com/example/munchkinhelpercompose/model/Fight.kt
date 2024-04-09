package com.example.munchkinhelpercompose.model

import androidx.annotation.IntRange
import androidx.compose.ui.graphics.Color
import com.example.munchkinhelpercompose.ui.theme.MHColor

typealias FightSide = Int

class Fight private constructor(
    private val sides: List<FightSide>
) {

    enum class Result(val color: Color) {
        WINNER(MHColor.positive),
        DRAW(MHColor.default),
        LOSSER(MHColor.negative)
    }

    companion object {
        const val MONSTER_SIDE = 1
        const val PLAYER_SIDE = 0
        private const val INITIAL_VALUE = 0
    }

    constructor() : this(listOf(INITIAL_VALUE, INITIAL_VALUE))
    constructor(playerPower: Int) : this(listOf(playerPower, INITIAL_VALUE))

    fun getValue(
        @IntRange(0, 1) side: FightSide
    ) = sides[side]

    fun getResult(
        @IntRange(0, 1) side: FightSide
    ) = with(sides.filter { it == sides.max() }) {
        if (this.size > 1) Result.DRAW
        else if (this[0] == sides[side]) Result.WINNER
        else Result.LOSSER
    }

    fun update(
        by: Int,
        @IntRange(0, 1) side: FightSide
    ) = Fight(sides.toMutableList().apply {
        set(side, this[side] + by)
    })
}