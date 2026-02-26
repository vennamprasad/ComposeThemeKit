package prasad.vennam.settings.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalElevations
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.design.theme.LocalMotionDurationScale

@Composable
fun ThemePreviewCard() {
    val dimensions = LocalDimensions.current
    val motionScale = LocalMotionDurationScale.current
    val elevations = LocalElevations.current
    val icons = LocalIcons.current
    var expanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevations.level2), 
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            // Fake Status Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensions.spacing.large)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            )

            // Fake App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensions.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(dimensions.iconSize.large)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
                Spacer(modifier = Modifier.width(dimensions.spacing.medium))
                Box(
                    modifier = Modifier
                        .height(dimensions.spacing.medium)
                        .width(dimensions.contentSize.extraLarge)
                        .background(
                            MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.extraSmall
                        )
                )
            }

            // Fake Content
            Row(modifier = Modifier.padding(horizontal = dimensions.spacing.medium)) {
                Column(modifier = Modifier.weight(1f)) {
                    // Fake List Item 1
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(dimensions.contentSize.avatar)
                                .background(
                                    MaterialTheme.colorScheme.secondaryContainer,
                                    MaterialTheme.shapes.small
                                )
                        )
                        Spacer(modifier = Modifier.width(dimensions.spacing.mediumSmall)) // 12dp
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(dimensions.spacing.mediumSmall)
                                    .width(dimensions.contentSize.medium)
                                    .background(
                                        MaterialTheme.colorScheme.onSurfaceVariant,
                                        MaterialTheme.shapes.extraSmall
                                    )
                            )
                            Spacer(modifier = Modifier.height(dimensions.spacing.extraSmall))
                            Box(
                                modifier = Modifier
                                    .height(dimensions.spacing.small)
                                    .width(dimensions.contentSize.extraLarge)
                                    .background(
                                        MaterialTheme.colorScheme.outline,
                                        MaterialTheme.shapes.extraSmall
                                    )
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = expanded, enter = expandVertically(
                            animationSpec = tween(durationMillis = (300 * motionScale).toInt())
                        ) + fadeIn(
                            animationSpec = tween(durationMillis = (300 * motionScale).toInt())
                        ), exit = shrinkVertically(
                            animationSpec = tween(durationMillis = (300 * motionScale).toInt())
                        ) + fadeOut(
                            animationSpec = tween(durationMillis = (300 * motionScale).toInt())
                        )
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(dimensions.spacing.medium))
                            // Fake List Item 2
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(dimensions.contentSize.avatar)
                                        .background(
                                            MaterialTheme.colorScheme.tertiaryContainer,
                                            MaterialTheme.shapes.small
                                        )
                                )
                                Spacer(modifier = Modifier.width(dimensions.spacing.mediumSmall))
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .height(dimensions.spacing.mediumSmall)
                                            .width(dimensions.contentSize.large)
                                            .background(
                                                MaterialTheme.colorScheme.onSurfaceVariant,
                                                MaterialTheme.shapes.extraSmall
                                            )
                                    )
                                    Spacer(modifier = Modifier.height(dimensions.spacing.extraSmall))
                                    Box(
                                        modifier = Modifier
                                            .height(dimensions.spacing.small)
                                            .width(dimensions.contentSize.small)
                                            .background(
                                                MaterialTheme.colorScheme.outline,
                                                MaterialTheme.shapes.extraSmall
                                            )
                                    )
                                }
                            }
                        }
                    }
                }

                // FAB
                FloatingActionButton(
                    onClick = { expanded = !expanded },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        if (expanded) icons.compress else icons.widgets,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensions.spacing.large))
        }
    }
}
