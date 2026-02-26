package prasad.vennam.model

import androidx.compose.runtime.Immutable

/**
 * A bundled combination of aesthetic options representing a cohesive "Profile" or "Variant".
 * 
 * E.g., "Cyberpunk" profile might forcefully set the background to True Black,
 * change the text to a Monospace font, and set the Brand color to Neon Yellow.
 *
 * @property id Unique identifier for the profile.
 * @property name Human-readable name displayed in the settings UI.
 * @property config The [ThemeConfig] mapping applied when this profile is active.
 * @property imageUrl Optional URL to fetch a cloud-hosted preview banner.
 * @property imageRes Optional compiled layout resource ID for the preview thumbnail.
 */
@Immutable
data class ThemeProfile(
    val id: String,
    val name: String,
    val config: ThemeConfig,
    val imageUrl: String? = null,
    val imageRes: Int? = null
)
