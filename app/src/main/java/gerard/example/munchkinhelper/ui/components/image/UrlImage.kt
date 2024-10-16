package gerard.example.munchkinhelper.ui.components.image

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun UrlImage(
    url: String,
    modifier: Modifier = Modifier
) = UrlImage(
    uri = Uri.parse(url),
    modifier = modifier
)

@Composable
fun UrlImage(
    uri: Uri,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(uri)
        .crossfade(true)
        .build(),
        contentDescription = contentDescription,
        modifier = modifier
    )
}