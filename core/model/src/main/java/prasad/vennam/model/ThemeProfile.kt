package prasad.vennam.model

/**
 * A curated bundle of theme configurations that can be applied all at once.
 *
 * Developers can register these in [ThemeRegistry] to offer one-tap "Skins" or "Modes"
 * (e.g., "Dark Mode", "High Contrast", "Emerald Theme").
 *
 * @property id Unique identifier for the profile.
 * @property name Human-readable name shown in the UI.
 * @property config The [ThemeConfig] mapping applied when this profile is active.
 */
data class ThemeProfile(
    val id: String,
    val name: String,
    val config: ThemeConfig
)
