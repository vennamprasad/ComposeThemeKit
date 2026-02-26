package prasad.vennam.settings.presentation.components

import androidx.compose.runtime.Composable
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.model.ThemeConfig
import prasad.vennam.settings.presentation.SettingsViewModel

@Composable
fun ScalesSettings(
    config: ThemeConfig,
    viewModel: SettingsViewModel
) {
    val icons = LocalIcons.current

    SettingsGroup(title = "Scales") {
        ScaleActionTile(
            icon = icons.formatShapes,
            label = "Shape Roundness",
            value = config.styleShapeScale,
            valueLabel = "${(config.styleShapeScale * 100).toInt()}%",
            onValueChange = viewModel::setStyleShapeScale
        )
        ScaleActionTile(
            icon = icons.compress, // Reusing compress icon for spacing
            label = "Content Spacing",
            value = config.styleSpacingScale,
            valueLabel = "${(config.styleSpacingScale * 100).toInt()}%",
            onValueChange = viewModel::setStyleSpacingScale
        )
        ScaleActionTile(
            icon = icons.fontDownload,
            label = "Text Size",
            value = config.styleTextScale,
            valueLabel = "${(config.styleTextScale * 100).toInt()}%",
            onValueChange = viewModel::setStyleTextScale
        )
    }
}
