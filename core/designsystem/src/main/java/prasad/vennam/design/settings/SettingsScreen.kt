package prasad.vennam.design.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.model.ThemeConfig
import prasad.vennam.design.settings.components.AppIconSettings
import prasad.vennam.design.settings.components.DisplaySettings
import prasad.vennam.design.settings.components.ModeSettings
import prasad.vennam.design.settings.components.MotionSettings
import prasad.vennam.design.settings.components.ProfileSettings
import prasad.vennam.design.settings.components.ScalesSettings
import prasad.vennam.design.settings.components.StyleSettings
import prasad.vennam.design.settings.components.ThemePreviewCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    options: ThemeOptions? = null,
) {
    val themeConfig by viewModel.themeConfig.collectAsState()
    val dimensions = LocalDimensions.current
    val actualOptions = options ?: rememberThemeOptions()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Settings",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
                    IconButton(onClick = { viewModel.resetToDefault() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reset to default"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(dimensions.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.large)
        ) {
            themeConfig?.let { config ->
                ThemeSettingsSection(
                    config = config, viewModel = viewModel, options = actualOptions
                )
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.extraLarge))
        }
    }
}

@Composable
fun ThemeSettingsSection(
    config: ThemeConfig, viewModel: SettingsViewModel, options: ThemeOptions
) {
    val dimensions = LocalDimensions.current

    // 1. Appearance (Header)
    Text(
        text = "Appearance",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = dimensions.spacing.small)
    )

    // 2. Mode (Light / Dark / System)
    ModeSettings(config = config, viewModel = viewModel)

    // 2.5 Profiles (Pre-sets)
    ProfileSettings(config = config, viewModel = viewModel, options = options)

    // 3. Style (Typography & Shape)
    StyleSettings(config = config, viewModel = viewModel, options = options)

    // 4. App Icon
    AppIconSettings(config = config, viewModel = viewModel, options = options)

    // 5. Motion & Feel
    MotionSettings(config = config, viewModel = viewModel, options = options)

    // 6. Display (True Black, Contrast, Compact)
    DisplaySettings(config = config, viewModel = viewModel)

    // 7. Scales
    ScalesSettings(config = config, viewModel = viewModel)

    // 8. Preview (Bottom)
    Spacer(modifier = Modifier.height(dimensions.spacing.medium))
    ThemePreviewCard()
}
