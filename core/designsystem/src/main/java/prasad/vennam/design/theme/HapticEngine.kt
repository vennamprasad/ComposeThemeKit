package prasad.vennam.design.theme

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import prasad.vennam.model.ThemeConfig

/**
 * A utility class to trigger haptic feedback based on the user's selected intensity from ThemeConfig.
 * Uses a Coroutine Channel to debounce rapid successive clicks and prevent hardware vibrator spam.
 */
class HapticEngine(
    context: Context,
    private val intensityId: String,
    private val coroutineScope: CoroutineScope
) {
    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private val clickChannel = Channel<Unit>(
        capacity = Channel.CONFLATED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        coroutineScope.launch {
            clickChannel.consumeAsFlow().collect {
                triggerVibration()
                // Drop any subsequent requests that arrive during this 50ms cooldown window
                delay(50L)
            }
        }
    }

    /**
     * Trigger a generic click haptic effect, scaled by the user's intensity preference.
     * Safe to call rapidly from Composable Event Handlers without blocking the main thread.
     */
    fun performClick() {
        if (intensityId == "none") return
        clickChannel.trySend(Unit)
    }

    private fun triggerVibration() {
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
    val coroutineScope = rememberCoroutineScope()
    
    return remember(context, themeConfig.hapticIntensityId, coroutineScope) {
        HapticEngine(context, themeConfig.hapticIntensityId, coroutineScope)
    }
}
