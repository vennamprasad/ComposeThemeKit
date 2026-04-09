package prasad.vennam.design.settings

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
 * The standard entry point for integrating AuraComposeThemeKit into an application.
 *
 * This provider performs the heavy lifting of state management and dependency injection:
 * 1. **Persistence Entry**: Automatically connects to the Hilt-managed `SettingsViewModel`.
 * 2. **State Collection**: Efficiently collects `ThemeConfig` updates using `collectAsStateWithLifecycle`.
 * 3. **Theming Context**: Wraps all child content in [ThemeKitTheme], which propagates colors, 
 *    typography, and scales via CompositionLocals.
 *
 * Use this at the very root of your Compose hierarchy (e.g., in `MainActivity.setContent`).
 *
 * @param themeRegistry The list of available theme options (Colors, Fonts, etc.). 
 * @param viewModel The Hilt ViewModel managing the theme state.
 * @param loadingContent The UI to display while the initial theme is being loaded from DataStore.
 * @param content The application UI that will be themed.
 */
@Composable
fun ThemeKitProvider(
    themeRegistry: ThemeRegistry? = null,
    viewModel: SettingsViewModel = hiltViewModel(),
    loadingContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val actualRegistry = themeRegistry ?: remember { ThemeRegistry() }
    val themeConfigState by viewModel.themeConfig.collectAsStateWithLifecycle()
    val isReady by viewModel.isReady.collectAsStateWithLifecycle()

    if (isReady) {
        // At this point, themeConfigState is guaranteed to be non-null 
        // because isReady depends on the first emission.
        val config = themeConfigState ?: ThemeConfig()
        ThemeKitTheme(
            themeRegistry = actualRegistry,
            themeConfig = config,
            content = content
        )
    } else {
        loadingContent()
    }
}
