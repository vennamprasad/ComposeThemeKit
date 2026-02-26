package prasad.vennam.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val spacing: Spacing = Spacing(),
    val iconSize: IconSize = IconSize(),
    val borderWidth: BorderWidth = BorderWidth(),
    val contentSize: ContentSize = ContentSize()
)

@Immutable
data class Spacing(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val mediumSmall: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
    val xxLarge: Dp = 48.dp,
    val xxxLarge: Dp = 64.dp
)

@Immutable
data class IconSize(
    val small: Dp = 18.dp,
    val medium: Dp = 24.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 48.dp
)

@Immutable
data class BorderWidth(
    val none: Dp = 0.dp,
    val hairLine: Dp = 0.5.dp,
    val thin: Dp = 1.dp,
    val thick: Dp = 2.dp,
    val heavy: Dp = 4.dp
)

@Immutable
data class ContentSize(
    val avatar: Dp = 40.dp,
    val small: Dp = 60.dp,
    val medium: Dp = 80.dp,
    val large: Dp = 100.dp,
    val extraLarge: Dp = 120.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }
