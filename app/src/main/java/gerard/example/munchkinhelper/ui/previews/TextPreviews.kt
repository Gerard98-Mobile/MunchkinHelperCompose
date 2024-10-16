package gerard.example.munchkinhelper.ui.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


private const val TEST_TEXT_SHORT = "short test text"

@Composable
@Preview
fun Default() = Column {
    Text(
        text = TEST_TEXT_SHORT,
    )
}

@Composable
@Preview
fun Label() = Column {
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.labelSmall
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.labelMedium
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
@Preview
fun Body() = Column {
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.bodySmall
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.bodyMedium
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.bodyLarge
    )
}


@Composable
@Preview
fun Title() = Column {
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.titleSmall
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
@Preview
fun Headline() = Column {
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.headlineSmall
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.headlineMedium
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
@Preview
fun Display() = Column {
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.displaySmall
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.displayMedium
    )
    Text(
        text = TEST_TEXT_SHORT,
        style = MaterialTheme.typography.displayLarge
    )
}


