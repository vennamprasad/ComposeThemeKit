package com.prasad.vennam.themekit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.prasad.vennam.themekit.sample.showcase.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import prasad.vennam.design.settings.SettingsViewModel
import prasad.vennam.design.settings.ThemeKitProvider
import prasad.vennam.design.settings.rememberThemeOptions
import prasad.vennam.model.ThemeColor
import prasad.vennam.model.registry.ThemeRegistry

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        installSplashScreen().setKeepOnScreenCondition {
            !viewModel.isReady.value
        }
        
        enableEdgeToEdge()
        setContent {
            ThemeKitProvider(viewModel = viewModel) {
                MainScreen(
                    viewModel = viewModel, options = rememberThemeOptions()
                )
            }
        }
    }
}
