package com.example.munchkinhelpercompose.presenter.game_container.winner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun WinnerScreen(
    navController: NavController,
) = Box {

    val party = Party(
        emitter = Emitter(duration = 10, TimeUnit.SECONDS).perSecond(100)
    )

    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(party),
    )
}