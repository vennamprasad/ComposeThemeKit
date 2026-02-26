package prasad.vennam.model

import androidx.compose.runtime.Immutable

/**
 * Represents a typography stack option available in the theme registry.
 *
 * @property id Unique identifier for the font (stored in [ThemeConfig]).
 * @property name Human-readable name displayed in the settings UI.
 * @property fontRes Optional embedded font resource ID.
 * @property isSystemDefault True if the library should fallback to standard Roboto/San Francisco.
 */
@Immutable
data class ThemeFont(
    val id: String,
    val name: String,
    val fontRes: Int? = null,
    val isSystemDefault: Boolean = false
)
