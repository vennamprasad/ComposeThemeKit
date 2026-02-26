package com.prasad.vennam.themekit.sample.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import prasad.vennam.design.components.ThemeBottomSheet
import prasad.vennam.design.components.ThemeButton
import prasad.vennam.design.components.ThemeCard
import prasad.vennam.design.components.ThemeCheckbox
import prasad.vennam.design.components.ThemeDialog
import prasad.vennam.design.components.ThemeLineChart
import prasad.vennam.design.components.ThemeProgressCircle
import prasad.vennam.design.components.ThemeSnackbar
import prasad.vennam.design.components.ThemeSwitch
import prasad.vennam.design.components.getSnackbarContainerColor
import prasad.vennam.design.components.getSnackbarContentColor
import prasad.vennam.design.theme.LocalAnimations
import prasad.vennam.design.theme.LocalDimensions
import prasad.vennam.design.theme.LocalIcons
import prasad.vennam.design.theme.LocalSemanticColors

/**
 * A realistic, mock dashboard meant to be used on a developer's GitHub portfolio
 * to showcase exactly how dynamic the `ThemeKit` system is when applied to a real product interface.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFeedScreen(
    isDarkTheme: Boolean = false,
    onToggleDarkMode: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    val semantics = LocalSemanticColors.current
    val icons = LocalIcons.current
    val animations = LocalAnimations.current
    val scope = rememberCoroutineScope()

    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var agreedToTerms by remember { mutableStateOf(false) }
    var showTransferDialog by remember { mutableStateOf(false) }
    var showAccountSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val snackbarHostState = remember { SnackbarHostState() }

    if (showTransferDialog) {
        ThemeDialog(
            onDismissRequest = { showTransferDialog = false },
            title = { Text("Transfer Complete") },
            text = { Text("Your funds have been successfully transferred to the designated account. The transaction will appear on your statement shortly.") },
            icon = {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = semantics.success,
                    modifier = Modifier.size(48.dp)
                )
            },
            confirmButton = {
                ThemeButton(onClick = { showTransferDialog = false }) {
                    Text("Done")
                }
            }
        )
    }

    // --- Bottom Sheet Showcase ---
    if (showAccountSheet) {
        ThemeBottomSheet(
            onDismissRequest = { showAccountSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensions.spacing.large)
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    "Select Account",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(dimensions.spacing.medium))
                repeat(3) { index ->
                    ThemeCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensions.spacing.small)
                            .clickable {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    showAccountSheet = false
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(dimensions.spacing.medium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(icons.settings, contentDescription = null)
                            Spacer(modifier = Modifier.width(dimensions.spacing.medium))
                            Column {
                                Text(
                                    "Savings Account ****${1234 + index}",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "$${1200 * (index + 1)}.00",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "Good morning,",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            "Jane Doe",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    Icon(
                        if (isDarkTheme) icons.lightMode else icons.darkMode,
                        contentDescription = "Toggle Dark Mode",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onToggleDarkMode(!isDarkTheme) }
                            .padding(dimensions.spacing.small)
                            .size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(dimensions.spacing.small))
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(end = dimensions.spacing.medium)
                            .size(36.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                ThemeSnackbar(
                    snackbarData = data,
                    containerColor = getSnackbarContainerColor(isSuccess = true),
                    contentColor = getSnackbarContentColor(isSuccess = true)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(dimensions.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium)
        ) {
            // 1. Balance Banner (ThemeCard)
            ThemeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAccountSheet = true },
                elevationLevel = 3 // High elevation for emphasis
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(dimensions.spacing.large)
                ) {
                    Text(
                        "Total Balance",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        "$14,235.00",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(dimensions.spacing.medium))
                    Row(horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)) {
                        ThemeButton(
                            onClick = { showTransferDialog = true },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Send")
                        }
                        ThemeButton(onClick = { /* Demo */ }, modifier = Modifier.weight(1f)) {
                            Text("Request")
                        }
                    }
                }
            }

            // 2. Data Visualization (The Wow Factor)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.medium)
            ) {
                // Spending Analysis Chart
                ThemeCard(
                    modifier = Modifier.weight(1.5f),
                    elevationLevel = 1
                ) {
                    Column(modifier = Modifier.padding(dimensions.spacing.medium)) {
                        Text(
                            "Spending Analysis",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(dimensions.spacing.small))
                        ThemeLineChart(
                            dataPoints = listOf(10f, 40f, 25f, 60f, 45f, 80f, 55f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }
                }

                // Monthly Goal Progress
                ThemeCard(
                    modifier = Modifier.weight(1f),
                    elevationLevel = 1
                ) {
                    Column(
                        modifier = Modifier.padding(dimensions.spacing.medium),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Monthly Goal",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(dimensions.spacing.medium))
                        ThemeProgressCircle(
                            progress = 0.75f,
                            size = 80.dp,
                            strokeWidth = 8.dp
                        )
                    }
                }
            }

            // 3. Status Indicators (SemanticColors)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.small)
            ) {
                StatusPill("Secure", semantics.success, semantics.onSuccess, Modifier.weight(1f))
                StatusPill("1 Alert", semantics.warning, semantics.onWarning, Modifier.weight(1f))
            }

            // 4. User Preferences (ThemeSwitch / ThemeCheckbox)
            Text(
                "Quick Settings",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = dimensions.spacing.small)
            )

            ThemeCard(
                modifier = Modifier.fillMaxWidth(),
                elevationLevel = 1
            ) {
                Column(
                    modifier = Modifier.padding(dimensions.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(dimensions.spacing.small)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(" Push Notifications", style = MaterialTheme.typography.bodyLarge)
                        }
                        ThemeSwitch(
                            checked = isNotificationsEnabled,
                            onCheckedChange = { isNotificationsEnabled = it }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ThemeCheckbox(
                            checked = agreedToTerms,
                            onCheckedChange = { agreedToTerms = it }
                        )
                        Text(
                            " I agree to the totally real terms of service.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimensions.spacing.extraLarge))
        }
    }
}

@Composable
fun StatusPill(
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(containerColor)
            .padding(vertical = dimensions.spacing.small, horizontal = dimensions.spacing.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = contentColor,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@prasad.vennam.design.theme.ThemeKitPreview
@Composable
private fun HomeFeedScreenPreview() {
    prasad.vennam.design.theme.ThemeKitTheme {
        HomeFeedScreen()
    }
}
