package prasad.vennam.model

import androidx.compose.runtime.Immutable

/**
 * Represents a brand color option available in the theme registry.
 *
 * @property id Unique identifier for the color (stored in [ThemeConfig]).
 * @property name Human-readable name displayed in the settings UI.
 * @property colorValue The physical color value as an ARGB Long (e.g., 0xFF0F52BA). Null means it relies on dynamic extraction.
 */
@Immutable
data class ThemeColor(
    val id: String,
    val name: String,
    val colorValue: Long?
)
