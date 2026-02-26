package prasad.vennam.model

/**
 * Defines a custom font family that can be registered in [ThemeRegistry].
 *
 * @property id Unique identifier for the font (stored in [ThemeConfig]).
 * @property name Human-readable name shown in the UI.
 * @property fontRes Optional resource ID for a font family (e.g., R.font.poppins).
 * @property isSystemDefault If true, uses the standard Android platform font.
 */
data class ThemeFont(
    val id: String,
    val name: String,
    val fontRes: Int? = null,
    val isSystemDefault: Boolean = false
)
