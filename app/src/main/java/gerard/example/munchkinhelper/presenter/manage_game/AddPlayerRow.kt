package gerard.example.munchkinhelper.presenter.manage_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.components.SpacerW
import gerard.example.munchkinhelper.ui.components.image.ResIcon

@Composable
fun AddPlayerRow(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            SpacerW(dp = 5.dp)
            ResIcon(
                id = R.drawable.ic_add,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
@Preview
private fun AddPlayerRowPreview() {
    AddPlayerRow(name = "Add player")
}
