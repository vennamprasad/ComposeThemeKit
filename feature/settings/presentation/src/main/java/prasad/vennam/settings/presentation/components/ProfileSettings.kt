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
import prasad.vennam.model.ThemeConfig
import prasad.vennam.settings.presentation.SettingsViewModel
import prasad.vennam.settings.presentation.ThemeOptions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileSettings(
    config: ThemeConfig,
    viewModel: SettingsViewModel,
    options: ThemeOptions
) {
    val dimensions = LocalDimensions.current
    val icons = LocalIcons.current

    SettingsGroup(title = "Pre-set Profiles") {
        Column(modifier = Modifier.padding(dimensions.spacing.medium)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icons.palette, 
                    contentDescription = null, 
                    tint = MaterialTheme.colorScheme.primary, 
                    modifier = Modifier.size(dimensions.iconSize.small)
                )
                Spacer(modifier = Modifier.width(dimensions.spacing.small))
                Text("Bundled Themes", style = MaterialTheme.typography.titleSmall)
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.small))
            Text(
                text = "Apply a curated set of theme configurations instantly.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(dimensions.spacing.small))
            
            FlowRow(horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)) {
                options.availableProfiles.forEach { profile ->
                    val isSelected = config.isSameAs(profile.config)
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.applyProfile(profile) },
                        label = { Text(profile.name) }
                    )
                }
            }
        }
    }
}

private fun ThemeConfig.isSameAs(other: ThemeConfig): Boolean {
    return this.isDarkTheme == other.isDarkTheme &&
            this.isTrueBlack == other.isTrueBlack &&
            this.isHighContrast == other.isHighContrast &&
            this.brandColorId == other.brandColorId &&
            this.fontFamilyId == other.fontFamilyId &&
            this.uiStyleId == other.uiStyleId &&
            this.hapticIntensityId == other.hapticIntensityId &&
            this.elevationStyleId == other.elevationStyleId &&
            this.iconStyleId == other.iconStyleId &&
            this.appIconId == other.appIconId &&
            this.styleShapeScale == other.styleShapeScale &&
            this.styleSpacingScale == other.styleSpacingScale &&
            this.styleTextScale == other.styleTextScale &&
            this.isCompactMode == other.isCompactMode &&
            this.animationScale == other.animationScale
}
