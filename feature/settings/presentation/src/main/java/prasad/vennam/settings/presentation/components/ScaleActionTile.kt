package prasad.vennam.settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import prasad.vennam.design.theme.LocalDimensions
import java.util.Locale

private const val MIN_SCALE = 0.5f
private const val MAX_SCALE = 2.0f

@Composable
fun ScaleActionTile(
    icon: ImageVector,
    label: String,
    value: Float,
    valueLabel: String? = null,
    onValueChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float> = MIN_SCALE..MAX_SCALE
) {
    val dimensions = LocalDimensions.current
    Column(
        modifier = Modifier.padding(
            horizontal = dimensions.spacing.medium, vertical = dimensions.spacing.medium
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(dimensions.iconSize.medium)
            )
            Spacer(modifier = Modifier.width(dimensions.spacing.medium))
            Text(
                text = label, style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = valueLabel ?: String.format(Locale.US, "%.1fx", value),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            modifier = Modifier.padding(start = dimensions.contentSize.avatar)
        )
    }
}
