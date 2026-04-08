package com.prasad.vennam.themekit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import prasad.vennam.settings.presentation.SettingsViewModel
import prasad.vennam.settings.presentation.ThemeKitProvider
import prasad.vennam.settings.presentation.rememberThemeOptions
import com.prasad.vennam.themekit.sample.showcase.MainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            // Using the new wrapper - everything is handled automatically!
            ThemeKitProvider {
                val themeOptions = rememberThemeOptions()
                
                MainScreen(
                    viewModel = viewModel,
                    options = themeOptions
                )
            }
        }
    }
}
