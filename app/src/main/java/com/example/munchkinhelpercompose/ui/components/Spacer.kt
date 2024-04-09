package com.example.munchkinhelpercompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpacerW(dp: Dp) = Spacer(modifier = Modifier.width(dp))

@Composable
fun SpacerH(dp: Dp) = Spacer(modifier = Modifier.height(dp))

enum class GradientDirection(
    val colorStops: @Composable () -> Array<Pair<Float, Color>>
) {
    FROM_START({
        arrayOf(
            0f to MaterialTheme.colorScheme.background,
            1f to Color.Transparent,
        )
    }),
    FROM_END({
        arrayOf(
            0f to Color.Transparent,
            1f to MaterialTheme.colorScheme.background,
        )
    })
}

@Composable
fun TransparentGradientSpacerH(
    modifier: Modifier = Modifier,
    dp: Dp = 5.dp,
    direction: GradientDirection = GradientDirection.FROM_START,
) = Spacer(
    modifier = modifier
        .height(dp)
        .fillMaxWidth()
        .applyGradientBrush(direction)
)

@Composable
fun TransparentGradientSpacerW(
    dp: Dp = 5.dp,
    direction: GradientDirection = GradientDirection.FROM_START
) = Spacer(
    modifier = Modifier
        .width(dp)
        .fillMaxHeight()
        .applyGradientBrush(direction)
)

@Composable
private fun Modifier.applyGradientBrush(direction: GradientDirection) = this.background(
    brush = Brush.verticalGradient(colorStops = direction.colorStops())
)