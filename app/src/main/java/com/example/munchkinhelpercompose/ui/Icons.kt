package com.example.munchkinhelpercompose.ui

import androidx.compose.runtime.Composable
import com.example.munchkinhelpercompose.R
import com.example.munchkinhelpercompose.ui.components.image.ClickableResIcon

@Composable
fun Close(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_close, onClick = onClick)

@Composable
fun Back(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_back, onClick = onClick)

@Composable
fun Plus(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_add, onClick = onClick)

@Composable
fun Minus(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_minus, onClick = onClick)