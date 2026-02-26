package prasad.vennam.model

/**
 * Defines a custom brand color that can be registered in [ThemeRegistry].
 *
 * @property id Unique identifier for the color (stored in [ThemeConfig]).
 * @property name Human-readable name shown in the UI.
 * @property colorValue The 32-bit ARGB color value (e.g., 0xFF... ). 
 *                     If null, the engine uses dynamic colors from the system wallpaper.
 */
data class ThemeColor(
    val id: String,
    val name: String,
    val colorValue: Long? = null
)
