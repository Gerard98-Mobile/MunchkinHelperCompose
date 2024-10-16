package gerard.example.munchkinhelper.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gerard.example.munchkinhelper.R
import gerard.example.munchkinhelper.ui.components.image.ResImage

@Composable
fun Logo(modifier: Modifier = Modifier) = ResImage(id = R.drawable.logo, modifier = modifier)