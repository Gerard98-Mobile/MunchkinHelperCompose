package gerard.example.munchkinhelper.presenter.manage_game

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.components.image.ClickableResIcon

@Composable
fun PlayerRow(
    name: String,
    modifier: Modifier = Modifier,
    onRemoveClick: () -> Unit = {}
) {
    Card(
        modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            ClickableResIcon(id = R.drawable.ic_minus) {
                onRemoveClick()
            }
        }
    }
}

@Composable
@Preview
private fun PlayerRowPreview() {
    PlayerRow(name = "Test")
}
