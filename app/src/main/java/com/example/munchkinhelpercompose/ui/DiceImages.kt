package com.example.munchkinhelpercompose.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.image.ResImage


@Composable
fun Dice(value: Int, modifier: Modifier = Modifier) = when(value) {
    1 -> DiceOne(modifier)
    2 -> DiceTwo(modifier)
    3 -> DiceThree(modifier)
    4 -> DiceFour(modifier)
    5 -> DiceFive(modifier)
    6 -> DiceSix(modifier)
    else -> throw IllegalArgumentException("Value have to be between 1-6 as dice don't have other sides")
}

@Composable
fun DiceOne(modifier: Modifier = Modifier) = ResImage(id = R.drawable.ic_dice_one, modifier = modifier)

@Composable
fun DiceTwo(modifier: Modifier = Modifier) = ResImage(id = R.drawable.ic_dice_two, modifier = modifier)

@Composable
fun DiceThree(modifier: Modifier = Modifier) = ResImage(id = R.drawable.ic_dice_three, modifier = modifier)

@Composable
fun DiceFour(modifier: Modifier = Modifier) = ResImage(id = R.drawable.ic_dice_four, modifier = modifier)

@Composable
fun DiceFive(modifier: Modifier = Modifier) = ResImage(id = R.drawable.ic_dice_five, modifier = modifier)

@Composable
fun DiceSix(modifier: Modifier = Modifier) = ResImage(id = R.drawable.ic_dice_six, modifier = modifier)