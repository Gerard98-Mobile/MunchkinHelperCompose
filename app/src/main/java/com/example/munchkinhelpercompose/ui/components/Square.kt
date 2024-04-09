package com.example.munchkinhelpercompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MinSquareCard(
    content: @Composable ColumnScope.() -> Unit
){
    Column(Modifier.width(IntrinsicSize.Min)){
        Card(Modifier.aspectRatio(1f)) {
            content()
        }
    }
}