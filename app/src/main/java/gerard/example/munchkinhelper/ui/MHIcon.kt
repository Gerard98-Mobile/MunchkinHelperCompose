package gerard.example.munchkinhelper.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.components.image.ClickableResIcon
import gerard.example.munchkinhelper.ui.components.image.ResIcon


object MHIcon {

    @Composable
    fun Close(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_close, onClick = onClick)

    @Composable
    fun Back(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_back, onClick = onClick)

    @Composable
    fun Plus(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_add, onClick = onClick)

    @Composable
    fun Shield(modifier: Modifier = Modifier) = ResIcon(modifier = modifier, id = R.drawable.ic_shield, tint = MaterialTheme.colorScheme.primary)

}
