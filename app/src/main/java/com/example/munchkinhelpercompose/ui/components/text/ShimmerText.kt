package com.example.munchkinhelpercompose.ui.components.text

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.munchkinhelpercompose.ui.theme.MHColor

private const val DURATION_MS = 1000

@Composable
fun ShimmerText(
    text: String,
    style: TextStyle = LocalTextStyle.current,
    colorStops: Array<Pair<Float, Color>> = arrayOf(
        0.0f to MaterialTheme.colorScheme.primary,
        1f to MHColor.crown
    )
) {
    val currentFontSizePx = with(LocalDensity.current) { style.fontSize.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val offset by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(tween(DURATION_MS, easing = LinearEasing)),
        label = "shimmer_text"
    )

    val brush = Brush.linearGradient(
        colorStops = colorStops,
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )

    Text(
        text = text,
        style = style.copy(
            brush = brush,
        ),
        textAlign = TextAlign.Center
    )
}