package prasad.vennam.design.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * A multipreview annotation that developers can use to instantly render
 * Compose UI elements in both Light and Dark mode variations of the ThemeKit engine.
 *
 * Usage:
 * ```kotlin
 * @ThemeKitPreview
 * @Composable
 * fun MyComponentPreview() {
 *     MyApplicationTheme { MyComponent() }
 * }
 * ```
 */
@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
annotation class ThemeKitPreview
