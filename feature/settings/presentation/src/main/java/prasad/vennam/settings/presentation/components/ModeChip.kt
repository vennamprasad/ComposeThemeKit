package prasad.vennam.settings.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import prasad.vennam.design.theme.LocalDimensions

@Composable
fun ModeChip(
    selected: Boolean, label: String, icon: ImageVector, onClick: () -> Unit
) {
    val dimensions = LocalDimensions.current
    FilterChip(selected = selected, onClick = onClick, label = { Text(label) }, leadingIcon = {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensions.iconSize.small)
        )
    })
}
