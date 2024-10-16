package gerard.example.munchkinhelper.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    @StringRes stringRes: Int,
    image: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SpacerW(dp = 5.dp)
        Text(
            text = stringResource(id = stringRes),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.weight(1f))
        image()
    }
}