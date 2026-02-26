package prasad.vennam.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import prasad.vennam.design.theme.LocalDimensions

/**
 * A highly robust TextField component adapting structural padding via ThemeConfig scaling parameters.
 */
@Composable
fun ThemeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.small, // Auto scaled by Theme.kt
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    val dimensions = LocalDimensions.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.padding(vertical = dimensions.spacing.small),
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        shape = shape,
        colors = colors
    )
}
