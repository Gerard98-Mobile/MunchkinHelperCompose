package gerard.example.munchkinhelper.ui.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CounterText(
    text: String,
    max: Int
) {
    Text(
        text = "${text.length} / $max",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
    )
}
