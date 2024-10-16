package gerard.example.munchkinhelper.presenter.game_container.winner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.components.ColumnCentered
import gerard.example.munchkinhelper.ui.components.SpacerH
import gerard.example.munchkinhelper.ui.components.animation.RawResLottieAnimation
import gerard.example.munchkinhelper.util.str
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun WinnerDialogContentPreview() {
    WinnerDialogContent(
        winnerName = "Gerard"
    )
}

@Composable
fun WinnerDialog(
    winnerName: String,
    dismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        WinnerDialogContent(
            winnerName = winnerName,
            dismiss = dismiss
        )
    }
}

@Composable
private fun WinnerDialogContent(
    winnerName: String,
    dismiss: () -> Unit = { }
) = Box(
    Modifier.clickable {
        dismiss()
    }
) {
    Column(Modifier.align(Alignment.Center)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 30.dp,
                    end = 30.dp
                )
        ) {
            ColumnCentered(
                Modifier.padding(10.dp)
            ) {
                RawResLottieAnimation(
                    animationRes = R.raw.winner_animation,
                    modifier = Modifier.size(300.dp)
                )

                Text(
                    text = R.string.congratulations.str(),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = winnerName,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = R.string.you_win.str(winnerName),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
        SpacerH(dp = 10.dp)
        Text(
            text = "click anywhere to continue",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }


    val party = Party(
        emitter = Emitter(duration = 10, TimeUnit.SECONDS).perSecond(100),
        spread = 90
    )

    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(
            party.copy(
                position = Position.Relative(0.0, 0.0),
                angle = 45
            )
        ),
    )

    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(
            party.copy(
                position = Position.Relative(1.0, 0.0),
                angle = 135
            )
        ),
    )

}