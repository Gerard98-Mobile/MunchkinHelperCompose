package com.example.munchkinhelpercompose.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
) {
    var multiplier by remember { mutableFloatStateOf(1f) }

    Text(
        text = text,
        maxLines = 1, // modify to fit your need
        overflow = TextOverflow.Visible,
        style = style.copy(
            fontSize = style.fontSize * multiplier
        ),
        onTextLayout = {
            if (it.hasVisualOverflow) {
                multiplier *= 0.99f // you can tune this constant
            }
        },
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color
    )
}
