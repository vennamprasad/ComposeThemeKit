package prasad.vennam.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalIcons

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorGrid(
    colors: List<prasad.vennam.model.ThemeColor>, selectedColorId: String, onColorSelected: (String) -> Unit
) {
    val dimensions = LocalDimensions.current
    val icons = LocalIcons.current
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
        modifier = Modifier.fillMaxWidth()
    ) {
        colors.forEach { themeColor ->
            val colorValue = themeColor.colorValue
            val isSelected = selectedColorId == themeColor.id
            val color =
                if (colorValue != null) Color(colorValue) else MaterialTheme.colorScheme.surfaceVariant

            Box(
                modifier = Modifier
                    .size(dimensions.iconSize.extraLarge)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = if (isSelected) dimensions.borderWidth.thick else dimensions.borderWidth.thin,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(themeColor.id) }, contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = icons.check,
                        contentDescription = "Selected",
                        tint = if (colorValue != null) Color.White else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(dimensions.iconSize.medium)
                    )
                } else if (colorValue == null) {
                    Icon(
                        imageVector = icons.autoMode,
                        contentDescription = "Dynamic",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(dimensions.iconSize.medium)
                    )
                }
            }
        }
    }
}
