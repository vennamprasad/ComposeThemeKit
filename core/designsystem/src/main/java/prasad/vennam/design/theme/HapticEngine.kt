package prasad.vennam.design.theme

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import prasad.vennam.model.ThemeConfig

/**
 * A utility class to trigger haptic feedback based on the user's selected intensity from ThemeConfig.
 */
class HapticEngine(
    private val context: Context,
    private val intensityId: String
) {
    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    /**
     * Trigger a generic click haptic effect, scaled by the user's intensity preference.
     */
    fun performClick() {
        if (intensityId == "none") return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val effect = when (intensityId) {
                "light" -> VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
                "heavy" -> VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
                else -> VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK) // medium/default
            }
            vibrator?.vibrate(effect)
        } else {
            @Suppress("DEPRECATION")
            when (intensityId) {
                "light" -> vibrator?.vibrate(10)
                "heavy" -> vibrator?.vibrate(50)
                else -> vibrator?.vibrate(20)
            }
        }
    }
}

@Composable
fun rememberHapticEngine(themeConfig: ThemeConfig): HapticEngine {
    val context = LocalContext.current
    return remember(context, themeConfig.hapticIntensityId) {
        HapticEngine(context, themeConfig.hapticIntensityId)
    }
}
