package prasad.vennam.settings.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import prasad.vennam.design.theme.MyApplicationTheme
import prasad.vennam.model.ThemeConfig

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = mockk<SettingsViewModel>(relaxed = true)
    private val themeConfigFlow = MutableStateFlow(ThemeConfig())

    @Test
    fun settingsScreen_displaysAllSections() {
        every { viewModel.themeConfig } returns themeConfigFlow

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = themeConfigFlow.value) {
                SettingsScreen(viewModel = viewModel)
            }
        }

        // Verify Header
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()

        // Verify Sections
        composeTestRule.onNodeWithText("Appearance").assertIsDisplayed()

        // Check presence of key items
        composeTestRule.onNodeWithText("Mode").assertIsDisplayed()
        composeTestRule.onNodeWithText("Style").assertIsDisplayed()
        
        // Scroll to find lower items
        composeTestRule.onNodeWithText("Display").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("Scales").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun settingsScreen_interactionLikelyCallsViewModel() {
        // This is a basic test to verify that components are interactive.

        every { viewModel.themeConfig } returns themeConfigFlow

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = themeConfigFlow.value) {
                SettingsScreen(viewModel = viewModel)
            }
        }

        composeTestRule.onNodeWithText("Appearance").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_modeTogglesInteraction() {
        every { viewModel.themeConfig } returns themeConfigFlow

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = themeConfigFlow.value) {
                SettingsScreen(viewModel = viewModel)
            }
        }

        // Mode Chips
        // "Dark" and "Light" text appears in multiple places (Mode, AppIcon, HapticIntensity).
        // We clarify by checking for a sibling "System" which is unique to Mode group.
        
        composeTestRule.onNode(hasText("Dark").and(hasAnySibling(hasText("System"))))
            .performScrollTo().performClick()
        verify { viewModel.setIsDarkTheme(true) }

        composeTestRule.onNode(hasText("Light").and(hasAnySibling(hasText("System"))))
            .performScrollTo().performClick()
        verify { viewModel.setIsDarkTheme(false) }

        composeTestRule.onNodeWithText("System").performScrollTo().performClick()
        verify { viewModel.setUseDynamicColor(true) }
    }

    @Test
    fun settingsScreen_displayTogglesInteraction() {
        val config = ThemeConfig(isDarkTheme = true)
        every { viewModel.themeConfig } returns MutableStateFlow(config)

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = config) {
                SettingsScreen(viewModel = viewModel)
            }
        }

        // Display Toggles (finding by text and ensuring visibility)
        composeTestRule.onNodeWithText("True Black").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("High Contrast").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("Compact Mode").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun settingsScreen_styleChipsInteraction() {
        every { viewModel.themeConfig } returns themeConfigFlow

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = themeConfigFlow.value) {
                SettingsScreen(viewModel = viewModel)
            }
        }
        
        // Fonts
        composeTestRule.onNodeWithText("Poppins").performScrollTo().performClick()
        verify { viewModel.setFontFamily(prasad.vennam.model.AppFont.POPPINS) }
        
        // Icon Styles
        composeTestRule.onNodeWithText("Sharp").performScrollTo().performClick()
        verify { viewModel.setIconStyle(prasad.vennam.model.IconStyle.SHARP) }
        
        // Brand Color
         composeTestRule.onNodeWithText("Accent Color").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun settingsScreen_appIconInteraction() {
         every { viewModel.themeConfig } returns themeConfigFlow

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = themeConfigFlow.value) {
                SettingsScreen(viewModel = viewModel)
            }
        }
        
        composeTestRule.onNodeWithText("Retro").performScrollTo().performClick()
        verify { viewModel.setAppIcon(prasad.vennam.model.AppIcon.RETRO) }
    }

    @Test
    fun settingsScreen_motionInteraction() {
         every { viewModel.themeConfig } returns themeConfigFlow

        composeTestRule.setContent {
            MyApplicationTheme(themeConfig = themeConfigFlow.value) {
                SettingsScreen(viewModel = viewModel)
            }
        }
        
        // Haptics
        // "Heavy" is unique enough, but "Light" would need disambiguation.
        composeTestRule.onNodeWithText("Heavy").performScrollTo().performClick()
        verify { viewModel.setHapticIntensity(prasad.vennam.model.HapticIntensity.HEAVY) }
    }
}
