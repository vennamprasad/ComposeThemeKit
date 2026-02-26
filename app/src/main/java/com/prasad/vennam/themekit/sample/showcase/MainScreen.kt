package com.prasad.vennam.themekit.sample.showcase

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import prasad.vennam.settings.presentation.SettingsScreen
import prasad.vennam.settings.presentation.SettingsViewModel
import prasad.vennam.settings.presentation.ThemeOptions

enum class ShowcaseTab(val title: String) {
    HOME("Home"),
    SETTINGS("Settings")
}

@Composable
fun MainScreen(
    viewModel: SettingsViewModel,
    options: ThemeOptions,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf(ShowcaseTab.HOME) }
    val themeConfig by viewModel.themeConfig.collectAsState(initial = null)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text(ShowcaseTab.HOME.title) },
                    selected = selectedTab == ShowcaseTab.HOME,
                    onClick = { selectedTab = ShowcaseTab.HOME }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text(ShowcaseTab.SETTINGS.title) },
                    selected = selectedTab == ShowcaseTab.SETTINGS,
                    onClick = { selectedTab = ShowcaseTab.SETTINGS }
                )
            }
        },
    ) { paddingValues ->
        val screenModifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)

        val currentConfig = themeConfig ?: prasad.vennam.model.ThemeConfig()

        when (selectedTab) {
            ShowcaseTab.HOME -> HomeFeedScreen(
                isDarkTheme = currentConfig.isDarkTheme,
                onToggleDarkMode = { viewModel.setIsDarkTheme(it) },
                modifier = screenModifier
            )

            ShowcaseTab.SETTINGS -> SettingsScreen(
                viewModel = viewModel,
                options = options,
                modifier = screenModifier
            )
        }
    }
}
