package prasad.vennam.design.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FontDownload
import androidx.compose.material.icons.filled.FormatShapes
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AutoMode
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Compress
import androidx.compose.material.icons.outlined.Contrast
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.FontDownload
import androidx.compose.material.icons.outlined.FormatShapes
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SettingsBrightness
import androidx.compose.material.icons.outlined.Vibration
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoMode
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Compress
import androidx.compose.material.icons.rounded.Contrast
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.FontDownload
import androidx.compose.material.icons.rounded.FormatShapes
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SettingsBrightness
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material.icons.rounded.Widgets
import androidx.compose.material.icons.sharp.Android
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.AutoMode
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Compress
import androidx.compose.material.icons.sharp.Contrast
import androidx.compose.material.icons.sharp.DarkMode
import androidx.compose.material.icons.sharp.FontDownload
import androidx.compose.material.icons.sharp.FormatShapes
import androidx.compose.material.icons.sharp.Layers
import androidx.compose.material.icons.sharp.LightMode
import androidx.compose.material.icons.sharp.Palette
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material.icons.sharp.SettingsBrightness
import androidx.compose.material.icons.sharp.Vibration
import androidx.compose.material.icons.sharp.Widgets
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector

data class AppIcons(
    val lightMode: ImageVector,
    val darkMode: ImageVector,
    val settingsBrightness: ImageVector,
    val fontDownload: ImageVector,
    val widgets: ImageVector,
    val layers: ImageVector,
    val palette: ImageVector,
    val formatShapes: ImageVector,
    val vibration: ImageVector,
    val contrast: ImageVector,
    val compress: ImageVector,
    val android: ImageVector,
    val autoMode: ImageVector,
    val check: ImageVector,
    val arrowBack: ImageVector,
    val close: ImageVector,
    val settings: ImageVector
)

val LocalIcons = staticCompositionLocalOf<AppIcons> { error("No LocalIcons provided") }

fun getIcons(styleId: String): AppIcons {
    return when (styleId) {
        "filled" -> AppIcons(
            lightMode = Icons.Filled.LightMode,
            darkMode = Icons.Filled.DarkMode,
            settingsBrightness = Icons.Filled.SettingsBrightness,
            fontDownload = Icons.Filled.FontDownload,
            widgets = Icons.Filled.Widgets,
            layers = Icons.Filled.Layers,
            palette = Icons.Filled.Palette,
            formatShapes = Icons.Filled.FormatShapes,
            vibration = Icons.Filled.Vibration,
            contrast = Icons.Filled.Contrast,
            compress = Icons.Filled.Compress,
            android = Icons.Filled.Android,
            autoMode = Icons.Filled.AutoMode,
            check = Icons.Filled.Check,
            arrowBack = Icons.AutoMirrored.Filled.ArrowBack,
            close = Icons.Filled.Close,
            settings = Icons.Filled.Settings
        )
        "outlined" -> AppIcons(
            lightMode = Icons.Outlined.LightMode,
            darkMode = Icons.Outlined.DarkMode,
            settingsBrightness = Icons.Outlined.SettingsBrightness,
            fontDownload = Icons.Outlined.FontDownload,
            widgets = Icons.Outlined.Widgets,
            layers = Icons.Outlined.Layers,
            palette = Icons.Outlined.Palette,
            formatShapes = Icons.Outlined.FormatShapes,
            vibration = Icons.Outlined.Vibration,
            contrast = Icons.Outlined.Contrast,
            compress = Icons.Outlined.Compress,
            android = Icons.Outlined.Android,
            autoMode = Icons.Outlined.AutoMode,
            check = Icons.Outlined.Check,
            arrowBack = Icons.AutoMirrored.Outlined.ArrowBack,
            close = Icons.Outlined.Close,
            settings = Icons.Outlined.Settings
        )
        "sharp" -> AppIcons(
            lightMode = Icons.Sharp.LightMode,
            darkMode = Icons.Rounded.DarkMode,
            settingsBrightness = Icons.Rounded.SettingsBrightness,
            fontDownload = Icons.Rounded.FontDownload,
            widgets = Icons.Rounded.Widgets,
            layers = Icons.Rounded.Layers,
            palette = Icons.Rounded.Palette,
            formatShapes = Icons.Rounded.FormatShapes,
            vibration = Icons.Rounded.Vibration,
            contrast = Icons.Rounded.Contrast,
            compress = Icons.Rounded.Compress,
            android = Icons.Rounded.Android,
            autoMode = Icons.Rounded.AutoMode,
            check = Icons.Rounded.Check,
            arrowBack = Icons.AutoMirrored.Rounded.ArrowBack,
            close = Icons.Rounded.Close,
            settings = Icons.Rounded.Settings
        )
        else -> AppIcons(
            lightMode = Icons.Rounded.LightMode,
            darkMode = Icons.Sharp.DarkMode,
            settingsBrightness = Icons.Sharp.SettingsBrightness,
            fontDownload = Icons.Sharp.FontDownload,
            widgets = Icons.Sharp.Widgets,
            layers = Icons.Sharp.Layers,
            palette = Icons.Sharp.Palette,
            formatShapes = Icons.Sharp.FormatShapes,
            vibration = Icons.Sharp.Vibration,
            contrast = Icons.Sharp.Contrast,
            compress = Icons.Sharp.Compress,
            android = Icons.Rounded.Android,
            autoMode = Icons.Rounded.AutoMode,
            check = Icons.Rounded.Check,
            arrowBack = Icons.AutoMirrored.Rounded.ArrowBack,
            close = Icons.Rounded.Close,
            settings = Icons.Sharp.Settings
        )
    }
}
