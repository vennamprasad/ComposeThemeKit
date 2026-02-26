package com.prasad.vennam.themekit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import prasad.vennam.design.theme.ThemeKitTheme
import prasad.vennam.model.ThemeConfig
import prasad.vennam.model.registry.ThemeRegistry
import prasad.vennam.settings.presentation.SettingsViewModel
import prasad.vennam.settings.presentation.ThemeOptions
import com.prasad.vennam.themekit.sample.showcase.MainScreen
import prasad.vennam.model.ThemeColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val themeRegistry = ThemeRegistry()

        enableEdgeToEdge()
        setContent {
            val themeConfig by viewModel.themeConfig.collectAsState()

            val customThemeOptions = ThemeOptions(
                availableFonts = themeRegistry.fonts,
                availableIconStyles = themeRegistry.iconStyles,
                availableAppIcons = themeRegistry.appIcons,
                availableBrandColors = themeRegistry.colors,
                availableUiStyles = themeRegistry.uiStyles,
                availableHapticIntensities = themeRegistry.hapticIntensities,
                availableElevationStyles = themeRegistry.elevationStyles,
                availableProfiles = themeRegistry.profiles
            )

            ThemeKitTheme(
                themeRegistry = themeRegistry,
                themeConfig = themeConfig ?: ThemeConfig()
            ) {
                MainScreen(
                    viewModel = viewModel,
                    options = customThemeOptions
                )
            }
        }
    }
}
