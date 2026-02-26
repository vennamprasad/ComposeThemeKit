package prasad.vennam.design.theme

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import prasad.vennam.model.ThemeConfig
import prasad.vennam.model.registry.ThemeRegistry

private const val ALPHA_PRIMARY_CONTAINER_DARK = 0.3f
private const val ALPHA_PRIMARY_CONTAINER_LIGHT = 0.1f
private val COLOR_SURFACE_CONTAINER_HIGH_DARK = Color(0xFF121212)
private val COLOR_PRIMARY_CONTAINER_HIGH_CONTRAST_DARK = Color(0xFF333333)
private val COLOR_SURFACE_VARIANT_HIGH_CONTRAST_LIGHT = Color(0xFFEDEDED)
private val COLOR_PRIMARY_CONTAINER_HIGH_CONTRAST_LIGHT = Color(0xFFE0E0E0)

@Composable
fun ThemeKitTheme(
    themeRegistry: ThemeRegistry = ThemeRegistry(),
    themeConfig: ThemeConfig = ThemeConfig(),
    colorScheme: ColorScheme? = null,
    typography: Typography? = null,
    shapes: Shapes? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current

    val appColorScheme = colorScheme ?: getAppColorScheme(themeConfig, context, themeRegistry)
    val appShapes = shapes ?: getAppShapes(themeConfig)
    val appTypography = typography ?: getAppTypography(themeConfig, context.assets, themeRegistry)
    val dimensions = getAppDimensions(themeConfig)
    val elevations = getAppElevations(themeConfig)
    val icons = getIcons(themeConfig.iconStyleId)

    val hapticEngine = rememberHapticEngine(themeConfig)
    val semanticColors = getSemanticColors(themeConfig)
    val animations = ThemeAnimation(themeConfig.animationScale)

    ConfigureStatusBar(view, appColorScheme, themeConfig.isDarkTheme)

    CompositionLocalProvider(
        LocalThemeRegistry provides themeRegistry,
        LocalDimensions provides dimensions,
        LocalMotionDurationScale provides themeConfig.animationScale,
        LocalElevations provides elevations,
        LocalIcons provides icons,
        LocalHapticEngine provides hapticEngine,
        LocalSemanticColors provides semanticColors,
        LocalAnimations provides animations
    ) {
        MaterialTheme(
            colorScheme = appColorScheme,
            typography = appTypography,
            shapes = appShapes,
            content = content
        )
    }
}

@Composable
private fun ConfigureStatusBar(view: View, colorScheme: ColorScheme, isDarkTheme: Boolean) {
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }
}

private fun getAppColorScheme(themeConfig: ThemeConfig, context: Context, themeRegistry: ThemeRegistry): ColorScheme {
    val brandColorId = themeConfig.brandColorId
    val brandColorConfig = themeRegistry.colors.find { it.id == brandColorId }
    val brandColor = brandColorConfig?.colorValue

    var colorScheme = when {
        brandColor != null -> getBrandColorScheme(brandColor, themeConfig.isDarkTheme)
        themeConfig.useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (themeConfig.isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        themeConfig.isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    if (themeConfig.isTrueBlack && themeConfig.isDarkTheme) {
        colorScheme = applyTrueBlack(colorScheme)
    }

    if (themeConfig.isHighContrast) {
        colorScheme = applyHighContrast(colorScheme, themeConfig.isDarkTheme)
    }

    return colorScheme
}

private fun getBrandColorScheme(brandColor: Long, isDarkTheme: Boolean): ColorScheme {
    val color = Color(brandColor)
    return if (isDarkTheme) {
        DarkColorScheme.copy(
            primary = color,
            primaryContainer = color.copy(alpha = ALPHA_PRIMARY_CONTAINER_DARK),
            onPrimary = Color.White,
            onPrimaryContainer = Color.White
        )
    } else {
        LightColorScheme.copy(
            primary = color,
            primaryContainer = color.copy(alpha = ALPHA_PRIMARY_CONTAINER_LIGHT),
            onPrimary = Color.White,
            onPrimaryContainer = Color.Black
        )
    }
}

private fun applyTrueBlack(scheme: ColorScheme): ColorScheme {
    return scheme.copy(
        background = Color.Black,
        surface = Color.Black,
        surfaceContainer = Color.Black,
        surfaceContainerLow = Color.Black,
        surfaceContainerHigh = COLOR_SURFACE_CONTAINER_HIGH_DARK
    )
}

private fun applyHighContrast(scheme: ColorScheme, isDarkTheme: Boolean): ColorScheme {
    return if (isDarkTheme) {
        scheme.copy(
            background = Color.Black,
            onBackground = Color.White,
            surface = Color.Black,
            onSurface = Color.White,
            surfaceVariant = COLOR_SURFACE_CONTAINER_HIGH_DARK,
            onSurfaceVariant = Color.White,
            outline = Color.White,
            primaryContainer = COLOR_PRIMARY_CONTAINER_HIGH_CONTRAST_DARK,
            onPrimaryContainer = Color.White
        )
    } else {
        scheme.copy(
            background = Color.White,
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black,
            surfaceVariant = COLOR_SURFACE_VARIANT_HIGH_CONTRAST_LIGHT,
            onSurfaceVariant = Color.Black,
            outline = Color.Black,
            primaryContainer = COLOR_PRIMARY_CONTAINER_HIGH_CONTRAST_LIGHT,
            onPrimaryContainer = Color.Black
        )
    }
}

private fun getAppShapes(themeConfig: ThemeConfig): Shapes {
    val baseShapes = getShapes(themeConfig.uiStyleId)
    val scale = themeConfig.styleShapeScale
    return baseShapes.copy(
        small = RoundedCornerShape(baseShapes.small.topStart.toPx(Size.Unspecified, Density(1f)) * scale),
        medium = RoundedCornerShape(baseShapes.medium.topStart.toPx(Size.Unspecified, Density(1f)) * scale),
        large = RoundedCornerShape(baseShapes.large.topStart.toPx(Size.Unspecified, Density(1f)) * scale)
    )
}

private fun getAppTypography(themeConfig: ThemeConfig, assetManager: AssetManager, themeRegistry: ThemeRegistry): Typography {
    val baseTypography = getTypography(themeConfig.fontFamilyId, assetManager, themeRegistry)
    val scale = themeConfig.styleTextScale
    return baseTypography.copy(
        bodyLarge = baseTypography.bodyLarge.copy(fontSize = baseTypography.bodyLarge.fontSize * scale),
        bodyMedium = baseTypography.bodyMedium.copy(fontSize = baseTypography.bodyMedium.fontSize * scale),
        bodySmall = baseTypography.bodySmall.copy(fontSize = baseTypography.bodySmall.fontSize * scale),
        headlineLarge = baseTypography.headlineLarge.copy(fontSize = baseTypography.headlineLarge.fontSize * scale),
        headlineMedium = baseTypography.headlineMedium.copy(fontSize = baseTypography.headlineMedium.fontSize * scale),
        headlineSmall = baseTypography.headlineSmall.copy(fontSize = baseTypography.headlineSmall.fontSize * scale),
        labelLarge = baseTypography.labelLarge.copy(fontSize = baseTypography.labelLarge.fontSize * scale),
        labelMedium = baseTypography.labelMedium.copy(fontSize = baseTypography.labelMedium.fontSize * scale),
        labelSmall = baseTypography.labelSmall.copy(fontSize = baseTypography.labelSmall.fontSize * scale),
        titleLarge = baseTypography.titleLarge.copy(fontSize = baseTypography.titleLarge.fontSize * scale),
        titleMedium = baseTypography.titleMedium.copy(fontSize = baseTypography.titleMedium.fontSize * scale),
        titleSmall = baseTypography.titleSmall.copy(fontSize = baseTypography.titleSmall.fontSize * scale)
    )
}

private fun getAppDimensions(themeConfig: ThemeConfig): Dimensions {
    val compactFactor = if (themeConfig.isCompactMode) 0.75f else 1.0f
    val scale = themeConfig.styleSpacingScale * compactFactor

    val spacing = Spacing(
        extraSmall = 4.dp * scale,
        small = 8.dp * scale,
        mediumSmall = 12.dp * scale,
        medium = 16.dp * scale,
        large = 24.dp * scale,
        extraLarge = 32.dp * scale,
        xxLarge = 48.dp * scale,
        xxxLarge = 64.dp * scale
    )
    val contentSize = ContentSize(
        avatar = 40.dp * scale,
        small = 60.dp * scale,
        medium = 80.dp * scale,
        large = 100.dp * scale,
        extraLarge = 120.dp * scale
    )
    return Dimensions(spacing = spacing, contentSize = contentSize)
}

private fun getAppElevations(themeConfig: ThemeConfig): Elevations {
    return when (themeConfig.elevationStyleId) {
        "flat" -> Elevations(
            level0 = 0.dp, level1 = 0.dp, level2 = 0.dp, level3 = 0.dp, level4 = 0.dp, level5 = 0.dp
        )
        "high_contrast" -> Elevations(
            level0 = 0.dp, level1 = 4.dp, level2 = 8.dp, level3 = 12.dp, level4 = 16.dp, level5 = 24.dp
        )
        else -> Elevations(
            level0 = 0.dp, level1 = 1.dp, level2 = 3.dp, level3 = 6.dp, level4 = 8.dp, level5 = 12.dp
        )
    }
}

data class Elevations(
    val level0: Dp = 0.dp,
    val level1: Dp = 1.dp,
    val level2: Dp = 3.dp,
    val level3: Dp = 6.dp,
    val level4: Dp = 8.dp,
    val level5: Dp = 12.dp,
    val modal: Dp = 8.dp,
    val dialog: Dp = 6.dp
)

val LocalMotionDurationScale = staticCompositionLocalOf { 1f }
val LocalElevations = staticCompositionLocalOf { Elevations() }
val LocalHapticEngine = staticCompositionLocalOf<HapticEngine> { 
    error("No haptic engine provided.") 
}
val LocalThemeRegistry = staticCompositionLocalOf<ThemeRegistry> {
    error("No ThemeRegistry provided. Make sure your UI is wrapped in ThemeKitTheme().")
}
