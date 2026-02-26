package prasad.vennam.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import prasad.vennam.model.ThemeConfig

class ThemePreferencesDataSourceTest {

    private val dataStore = mockk<DataStore<Preferences>>(relaxed = true)

    @Test
    fun `themeConfig returns default values when preferences are empty`() = runTest {
        val preferences = preferencesOf()
        every { dataStore.data } returns flowOf(preferences)
        val dataSource = ThemePreferencesDataSource(dataStore)

        val config = dataSource.themeConfig.first()

        assertTrue(config.useDynamicColor)
        assertFalse(config.isDarkTheme)
        assertFalse(config.isHighContrast)
        assertEquals(1.0f, config.styleShapeScale)
        assertEquals(1.0f, config.styleSpacingScale)
        assertEquals(1.0f, config.styleTextScale)
        assertEquals("default", config.brandColorId)
    }

    @Test
    fun `setUseDynamicColor updates datastore`() = runTest {
        val dataSource = ThemePreferencesDataSource(dataStore)
        dataSource.setUseDynamicColor(false)
        
        coVerify { 
            dataStore.updateData(any()) 
        }
    }

    @Test
    fun `setBrandColorId updates datastore`() = runTest {
        val dataSource = ThemePreferencesDataSource(dataStore)
        val testColorId = "ocean"
        dataSource.setBrandColorId(testColorId)
        
        coVerify { 
            dataStore.updateData(any()) 
        }
    }
}
