package prasad.vennam.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import prasad.vennam.design.theme.LocalElevations

/**
 * A styled Card component that dynamically adjusts its elevation based on user preferences.
 * 
 * Instead of hardcoded Dp values, [ThemeCard] uses `elevationLevel` (0-5) which is resolved
 * against [LocalElevations]. This allows the entire app to instantly switch between
 * "Flat" and "High Contrast" styles via the settings.
 *
 * @param modifier Standard Compose modifier.
 * @param shape Structural geometry of the card.
 * @param colors Surface and content colors.
 * @param border Optional stroke around the card.
 * @param elevationLevel Logic intensity level (0: Flat, 1: Subtle, 5: Intense).
 * @param content The composable content inside the card.
 */
@Composable
fun ThemeCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: CardColors = CardDefaults.cardColors(),
    border: BorderStroke? = null,
    elevationLevel: Int = 1,
    content: @Composable () -> Unit
) {
    val elevations = LocalElevations.current
    
    val mappedElevation = when (elevationLevel) {
        0 -> elevations.level0
        1 -> elevations.level1
        2 -> elevations.level2
        3 -> elevations.level3
        4 -> elevations.level4
        else -> elevations.level5
    }

    Card(
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = CardDefaults.cardElevation(defaultElevation = mappedElevation),
        border = border,
        content = {
            content()
        }
    )
}
