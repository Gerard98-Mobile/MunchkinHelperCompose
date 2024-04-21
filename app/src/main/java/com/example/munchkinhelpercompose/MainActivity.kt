package com.example.munchkinhelpercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.munchkinhelpercompose.navigation.AppNavHost
import com.example.munchkinhelpercompose.ui.theme.AppTheme
import com.example.munchkinhelpercompose.ui.theme.AppThemeType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        splashScreen.setKeepOnScreenCondition { viewModel.state.value.isSplashVisible }

        setContent {
            MainActivityContent()
        }
    }
}

@Composable
fun MainActivityContent(
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    AppTheme(
        appThemeType = state.settings?.appThemeType ?: AppThemeType.AUTO
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box {
                if (state.settings != null) {
                    SideEffect {
                        viewModel.hideSplash()
                    }
                }
                AppNavHost()
            }
        }
    }
}