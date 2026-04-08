package prasad.vennam.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import prasad.vennam.design.theme.ThemeKitTheme
import prasad.vennam.model.ThemeConfig
import prasad.vennam.model.registry.ThemeRegistry

/**
 * A seamless wrapper that automatically injects the SettingsViewModel,
 * collects the ThemeConfig state, and provides it to the ThemeKitTheme.
 * 
 * This reduces the boilerplate in your MainActivity to just:
 * ThemeKitProvider {
 *     // App Content
 * }
 */
@Composable
fun ThemeKitProvider(
    themeRegistry: ThemeRegistry = remember { ThemeRegistry() },
    viewModel: SettingsViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val themeConfigState = viewModel.themeConfig.collectAsStateWithLifecycle()
    val config = themeConfigState.value ?: ThemeConfig()

    ThemeKitTheme(
        themeRegistry = themeRegistry,
        themeConfig = config,
        content = content
    )
}
