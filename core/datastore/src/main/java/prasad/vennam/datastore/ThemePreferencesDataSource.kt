package prasad.vennam.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import prasad.vennam.model.ThemeConfig
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ThemePreferencesDataSource @Inject constructor(
    @Named("ThemeDataStore") private val dataStore: DataStore<ThemeConfig>
) {
    val themeConfig: Flow<ThemeConfig> = dataStore.data

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
        dataStore.updateData { it.copy(useDynamicColor = useDynamicColor) }
    }

    suspend fun setIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.updateData { it.copy(isDarkTheme = isDarkTheme) }
    }
    
    suspend fun setIsHighContrast(isHighContrast: Boolean) {
        dataStore.updateData { it.copy(isHighContrast = isHighContrast) }
    }

    suspend fun setStyleShapeScale(scale: Float) {
        dataStore.updateData { it.copy(styleShapeScale = scale) }
    }

    suspend fun setStyleSpacingScale(scale: Float) {
        dataStore.updateData { it.copy(styleSpacingScale = scale) }
    }

    suspend fun setStyleTextScale(scale: Float) {
        dataStore.updateData { it.copy(styleTextScale = scale) }
    }

    suspend fun setBrandColorId(id: String) {
        dataStore.updateData { it.copy(brandColorId = id) }
    }

    suspend fun setFontFamilyId(id: String) {
        dataStore.updateData { it.copy(fontFamilyId = id) }
    }

    suspend fun setUiStyleId(id: String) {
        dataStore.updateData { it.copy(uiStyleId = id) }
    }

    suspend fun setIsTrueBlack(isTrueBlack: Boolean) {
        dataStore.updateData { it.copy(isTrueBlack = isTrueBlack) }
    }

    suspend fun setIsCompactMode(isCompactMode: Boolean) {
        dataStore.updateData { it.copy(isCompactMode = isCompactMode) }
    }

    suspend fun setAnimationScale(scale: Float) {
        dataStore.updateData { it.copy(animationScale = scale) }
    }

    suspend fun setHapticIntensityId(id: String) {
        dataStore.updateData { it.copy(hapticIntensityId = id) }
    }

    suspend fun setElevationStyleId(id: String) {
        dataStore.updateData { it.copy(elevationStyleId = id) }
    }

    suspend fun setAppIconId(id: String) {
        dataStore.updateData { it.copy(appIconId = id) }
    }

    suspend fun setIconStyleId(id: String) {
        dataStore.updateData { it.copy(iconStyleId = id) }
    }

    suspend fun updateConfig(config: ThemeConfig) {
        dataStore.updateData { config }
    }
}
