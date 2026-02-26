package prasad.vennam.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalSemanticColors

/**
 * A themed Snackbar that uses semantic colors (Success, Error, etc.)
 * and respects the global shape scaling.
 */
@Composable
fun ThemeSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = MaterialTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
    contentColor: Color = MaterialTheme.colorScheme.inverseSurface,
    actionColor: Color = MaterialTheme.colorScheme.inversePrimary,
    actionContentColor: Color = MaterialTheme.colorScheme.inversePrimary,
    dismissActionContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    val dimensions = LocalDimensions.current
    
    Snackbar(
        modifier = modifier.padding(dimensions.spacing.medium),
        action = snackbarData.visuals.actionLabel?.let { actionLabel ->
            {
                TextButton(onClick = { snackbarData.performAction() }) {
                    Text(actionLabel, color = actionColor)
                }
            }
        },
        dismissAction = if (snackbarData.visuals.withDismissAction) {
            {
                TextButton(onClick = { snackbarData.dismiss() }) {
                    Text("Dismiss", color = dismissActionContentColor)
                }
            }
        } else null,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        content = {
            Text(snackbarData.visuals.message)
        }
    )
}

/**
 * Helper to get a themed snackbar color based on semantic intent.
 */
@Composable
fun getSnackbarContainerColor(isError: Boolean = false, isSuccess: Boolean = false): Color {
    val semanticColors = LocalSemanticColors.current
    return when {
        isError -> semanticColors.error
        isSuccess -> semanticColors.success
        else -> MaterialTheme.colorScheme.inverseSurface
    }
}

@Composable
fun getSnackbarContentColor(isError: Boolean = false, isSuccess: Boolean = false): Color {
    val semanticColors = LocalSemanticColors.current
    return when {
        isError -> semanticColors.onError
        isSuccess -> semanticColors.onSuccess
        else -> MaterialTheme.colorScheme.inverseOnSurface
    }
}
