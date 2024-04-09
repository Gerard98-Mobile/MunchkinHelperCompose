package com.example.munchkinhelpercompose.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AnimatedCounter(
    value: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displaySmall,
    color: Color = MaterialTheme.colorScheme.primary,
){
    AnimatedContent(
        targetState = value,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { -it } togetherWith slideOutVertically { it }
            } else {
                slideInVertically { it } togetherWith slideOutVertically { -it }
            }
        },
        label = "counter",
        modifier = modifier
    ) {
        Text(
            text = it.toString(),
            style = style,
            color = color,
            textAlign = TextAlign.Center
        )
    }

}