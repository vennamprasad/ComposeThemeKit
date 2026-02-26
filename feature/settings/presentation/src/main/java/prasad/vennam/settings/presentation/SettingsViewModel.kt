package prasad.vennam.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import prasad.vennam.domain.AppIconRepository
import prasad.vennam.domain.ThemeRepository
import prasad.vennam.model.ThemeProfile
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val appIconRepository: AppIconRepository
) : ViewModel() {

    val themeConfig = themeRepository.themeConfig
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun setUseDynamicColor(useDynamicColor: Boolean) {
        viewModelScope.launch {
            themeRepository.setUseDynamicColor(useDynamicColor)
        }
    }

    fun setIsDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            themeRepository.setIsDarkTheme(isDarkTheme)
        }
    }

    fun setIsHighContrast(isHighContrast: Boolean) {
        viewModelScope.launch {
            themeRepository.setIsHighContrast(isHighContrast)
        }
    }

    fun setStyleShapeScale(scale: Float) {
        viewModelScope.launch {
            themeRepository.setStyleShapeScale(scale)
        }
    }

    fun setStyleSpacingScale(scale: Float) {
        viewModelScope.launch {
            themeRepository.setStyleSpacingScale(scale)
        }
    }

    fun setStyleTextScale(scale: Float) {
        viewModelScope.launch {
            themeRepository.setStyleTextScale(scale)
        }
    }

    fun setBrandColorId(id: String) {
        viewModelScope.launch {
            themeRepository.setBrandColorId(id)
        }
    }

    fun setFontFamilyId(id: String) {
        viewModelScope.launch {
            themeRepository.setFontFamilyId(id)
        }
    }

    fun setUiStyleId(id: String) {
        viewModelScope.launch {
            themeRepository.setUiStyleId(id)
        }
    }

    fun setIsTrueBlack(isTrueBlack: Boolean) {
        viewModelScope.launch {
            themeRepository.setIsTrueBlack(isTrueBlack)
        }
    }

    fun setIsCompactMode(isCompactMode: Boolean) {
        viewModelScope.launch {
            themeRepository.setIsCompactMode(isCompactMode)
        }
    }

    fun setAnimationScale(scale: Float) {
        viewModelScope.launch {
            themeRepository.setAnimationScale(scale)
        }
    }

    fun setHapticIntensityId(id: String) {
        viewModelScope.launch {
            themeRepository.setHapticIntensityId(id)
        }
    }

    fun setElevationStyleId(id: String) {
        viewModelScope.launch {
            themeRepository.setElevationStyleId(id)
        }
    }

    fun setAppIconId(id: String) {
        viewModelScope.launch {
            themeRepository.setAppIconId(id)
            appIconRepository.setAppIconId(id)
        }
    }

    fun setIconStyleId(id: String) {
        viewModelScope.launch {
            themeRepository.setIconStyleId(id)
        }
    }

    fun applyProfile(profile: ThemeProfile) {
        viewModelScope.launch {
            themeRepository.applyProfile(profile.config)
        }
    }
}
