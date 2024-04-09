package com.example.munchkinhelpercompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.image.ClickableResIcon
import com.example.munchkinhelpercompose.ui.components.image.ResIcon
import com.example.munchkinhelpercompose.ui.components.image.ResImage

@Composable
fun Logo(modifier: Modifier = Modifier) = ResImage(id = R.drawable.logo, modifier = modifier)