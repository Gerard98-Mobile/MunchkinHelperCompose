package gerard.example.munchkinhelper.ui.components.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun ClickableResIcon(modifier: Modifier = Modifier, @DrawableRes id: Int, contentDescription: String? = null, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        ResIcon(
            id = id,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}

@Composable
fun ResIcon(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    tint: Color = LocalContentColor.current,
    contentDescription: String? = null
) =
    Icon(
        painter = painterResource(id = id),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )

@Composable
fun ResImage(modifier: Modifier = Modifier, @DrawableRes id: Int, contentDescription: String? = null) =
    Image(
        painter = painterResource(id = id),
        contentDescription = contentDescription,
        modifier = modifier
    )