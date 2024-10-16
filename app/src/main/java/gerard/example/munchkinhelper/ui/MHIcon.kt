package gerard.example.munchkinhelper.ui

import androidx.compose.runtime.Composable
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.components.image.ClickableResIcon


object MHIcon {

    @Composable
    fun Close(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_close, onClick = onClick)

    @Composable
    fun Back(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_back, onClick = onClick)

    @Composable
    fun Plus(onClick: () -> Unit) = ClickableResIcon(id = R.drawable.ic_add, onClick = onClick)

}
