package prasad.vennam.model

import androidx.compose.runtime.Immutable

@Immutable
data class ThemeUiStyle(
    val id: String,
    val name: String
)

@Immutable
data class ThemeHapticIntensity(
    val id: String,
    val name: String
)

@Immutable
data class ThemeElevationStyle(
    val id: String,
    val name: String
)
