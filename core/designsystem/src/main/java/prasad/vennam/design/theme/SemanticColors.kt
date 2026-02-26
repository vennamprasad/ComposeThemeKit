package prasad.vennam.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import prasad.vennam.model.ThemeConfig

/**
 * Extended semantic colors beyond the base Material 3 ColorScheme.
 * Handful for success/warning/error semantic roles.
 */
data class SemanticColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color
)

val LocalSemanticColors = staticCompositionLocalOf {
    // Return a dummy empty block to prevent crashes if consumed before provided
    SemanticColors(
        success = Color.Unspecified, onSuccess = Color.Unspecified, successContainer = Color.Unspecified, onSuccessContainer = Color.Unspecified,
        warning = Color.Unspecified, onWarning = Color.Unspecified, warningContainer = Color.Unspecified, onWarningContainer = Color.Unspecified,
        error = Color.Unspecified, onError = Color.Unspecified, errorContainer = Color.Unspecified, onErrorContainer = Color.Unspecified,
        info = Color.Unspecified, onInfo = Color.Unspecified, infoContainer = Color.Unspecified, onInfoContainer = Color.Unspecified
    )
}

/**
 * Generate semantic colors adjusted for true black / high contrast properties.
 */
fun getSemanticColors(themeConfig: ThemeConfig): SemanticColors {
    val isDark = themeConfig.isDarkTheme

    // Base colors based on Material guidance
    var success = if (isDark) Color(0xFF81C784) else Color(0xFF388E3C)
    val onSuccess = if (isDark) Color(0xFF003300) else Color(0xFFFFFFFF)
    var successContainer = if (isDark) Color(0xFF1B5E20) else Color(0xFFC8E6C9)
    val onSuccessContainer = if (isDark) Color(0xFFA5D6A7) else Color(0xFF1B5E20)

    var warning = if (isDark) Color(0xFFFFB74D) else Color(0xFFF57C00)
    val onWarning = if (isDark) Color(0xFF4E342E) else Color(0xFFFFFFFF)
    var warningContainer = if (isDark) Color(0xFFE65100) else Color(0xFFFFE0B2)
    val onWarningContainer = if (isDark) Color(0xFFFFCC80) else Color(0xFFE65100)

    var error = if (isDark) Color(0xFFE57373) else Color(0xFFD32F2F)
    val onError = if (isDark) Color(0xFF4A0E0E) else Color(0xFFFFFFFF)
    var errorContainer = if (isDark) Color(0xFFB71C1C) else Color(0xFFFFCDD2)
    val onErrorContainer = if (isDark) Color(0xFFEF9A9A) else Color(0xFFB71C1C)

    var info = if (isDark) Color(0xFF64B5F6) else Color(0xFF1976D2)
    val onInfo = if (isDark) Color(0xFF0D47A1) else Color(0xFFFFFFFF)
    var infoContainer = if (isDark) Color(0xFF0D47A1) else Color(0xFFBBDEFB)
    val onInfoContainer = if (isDark) Color(0xFF90CAF9) else Color(0xFF0D47A1)

    if (themeConfig.isHighContrast) {
        if (isDark) {
            successContainer = Color(0xFF333333)
            warningContainer = Color(0xFF333333)
            errorContainer = Color(0xFF333333)
            infoContainer = Color(0xFF333333)
        } else {
            successContainer = Color(0xFFE0E0E0)
            warningContainer = Color(0xFFE0E0E0)
            errorContainer = Color(0xFFE0E0E0)
            infoContainer = Color(0xFFE0E0E0)
        }
    }

    return SemanticColors(
        success, onSuccess, successContainer, onSuccessContainer,
        warning, onWarning, warningContainer, onWarningContainer,
        error, onError, errorContainer, onErrorContainer,
        info, onInfo, infoContainer, onInfoContainer
    )
}
