package prasad.vennam.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.model.ThemeConfig
import prasad.vennam.settings.presentation.SettingsViewModel

import prasad.vennam.settings.presentation.ThemeOptions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StyleSettings(
    config: ThemeConfig,
    viewModel: SettingsViewModel,
    options: ThemeOptions
) {
    val dimensions = LocalDimensions.current
    val icons = LocalIcons.current

    SettingsGroup(title = "Style") {
        // Typography
        Column(modifier = Modifier.padding(dimensions.spacing.medium)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icons.fontDownload,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(dimensions.iconSize.small)
                )
                Spacer(modifier = Modifier.width(dimensions.spacing.small))
                Text("Typography", style = MaterialTheme.typography.titleSmall)
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.small))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)) {
                options.availableFonts.forEach { font ->
                    FilterChip(
                        selected = config.fontFamilyId == font.id,
                        onClick = { viewModel.setFontFamilyId(font.id) },
                        label = { Text(font.name) }
                    )
                }
            }
        }

        // Icon Style (New)
        Column(modifier = Modifier.padding(horizontal = dimensions.spacing.medium, vertical = dimensions.spacing.small)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icons.widgets,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(dimensions.iconSize.small)
                )
                Spacer(modifier = Modifier.width(dimensions.spacing.small))
                Text("Icon Style", style = MaterialTheme.typography.titleSmall)
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.small))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)) {
                options.availableIconStyles.forEach { style ->
                    FilterChip(
                        selected = config.iconStyleId == style.id,
                        onClick = { viewModel.setIconStyleId(style.id) },
                        label = { Text(style.name) }
                    ) 
                }
            }
        }

        // Brand Color
        Column(modifier = Modifier.padding(dimensions.spacing.medium)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icons.palette,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(dimensions.iconSize.small)
                )
                Spacer(modifier = Modifier.width(dimensions.spacing.small))
                Text("Accent Color", style = MaterialTheme.typography.titleSmall)
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.small))
            ColorGrid(
                colors = options.availableBrandColors,
                selectedColorId = config.brandColorId,
                onColorSelected = viewModel::setBrandColorId
            )
        }
    }
}
