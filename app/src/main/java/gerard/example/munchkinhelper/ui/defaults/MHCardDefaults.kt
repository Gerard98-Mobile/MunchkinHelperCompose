package gerard.example.munchkinhelper.ui.defaults

import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object MHCardDefaults {

    @Composable
    fun transparentCardColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = Color.Transparent,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = Color.Transparent,
    ): CardColors = CardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

}