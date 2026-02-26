package prasad.vennam.settings.presentation

import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import prasad.vennam.domain.AppIconRepository
import prasad.vennam.domain.ThemeRepository
import prasad.vennam.model.ThemeConfig

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val themeRepository = mockk<ThemeRepository>(relaxed = true)
    private val appIconRepository = mockk<AppIconRepository>(relaxed = true)
    private lateinit var viewModel: SettingsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { themeRepository.themeConfig } returns flowOf(ThemeConfig())
        viewModel = SettingsViewModel(themeRepository,appIconRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setUseDynamicColor calls repository`() = runTest {
        viewModel.setUseDynamicColor(false)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setUseDynamicColor(false) }
    }

    @Test
    fun `setIsDarkTheme calls repository`() = runTest {
        viewModel.setIsDarkTheme(true)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setIsDarkTheme(true) }
    }

    @Test
    fun `setIsHighContrast calls repository`() = runTest {
        viewModel.setIsHighContrast(true)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setIsHighContrast(true) }
    }

    @Test
    fun `setStyleShapeScale calls repository`() = runTest {
        viewModel.setStyleShapeScale(0.5f)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setStyleShapeScale(0.5f) }
    }

    @Test
    fun `setStyleSpacingScale calls repository`() = runTest {
        viewModel.setStyleSpacingScale(1.2f)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setStyleSpacingScale(1.2f) }
    }

    @Test
    fun `setStyleTextScale calls repository`() = runTest {
        viewModel.setStyleTextScale(1.1f)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setStyleTextScale(1.1f) }
    }

    @Test
    fun `setBrandColorId calls repository`() = runTest {
        val colorId = "emerald"
        viewModel.setBrandColorId(colorId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setBrandColorId(colorId) }
    }

    @Test
    fun `setFontFamilyId calls repository`() = runTest {
        val fontId = "poppins"
        viewModel.setFontFamilyId(fontId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setFontFamilyId(fontId) }
    }

    @Test
    fun `setUiStyleId calls repository`() = runTest {
        val styleId = "square"
        viewModel.setUiStyleId(styleId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setUiStyleId(styleId) }
    }

    @Test
    fun `setIsTrueBlack calls repository`() = runTest {
        viewModel.setIsTrueBlack(true)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setIsTrueBlack(true) }
    }

    @Test
    fun `setIsCompactMode calls repository`() = runTest {
        viewModel.setIsCompactMode(true)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setIsCompactMode(true) }
    }

    @Test
    fun `setAnimationScale calls repository`() = runTest {
        viewModel.setAnimationScale(0.5f)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setAnimationScale(0.5f) }
    }

    @Test
    fun `setHapticIntensityId calls repository`() = runTest {
        val intensityId = "heavy"
        viewModel.setHapticIntensityId(intensityId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setHapticIntensityId(intensityId) }
    }

    @Test
    fun `setElevationStyleId calls repository`() = runTest {
        val styleId = "high_contrast"
        viewModel.setElevationStyleId(styleId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setElevationStyleId(styleId) }
    }

    @Test
    fun `setAppIconId calls theme and icon repository`() = runTest {
        val iconId = "retro"
        viewModel.setAppIconId(iconId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { 
            themeRepository.setAppIconId(iconId)
            appIconRepository.setAppIconId(iconId)
        }
    }

    @Test
    fun `setIconStyleId calls repository`() = runTest {
        val styleId = "sharp"
        viewModel.setIconStyleId(styleId)
        testDispatcher.scheduler.advanceUntilIdle()
        coVerify { themeRepository.setIconStyleId(styleId) }
    }
}
