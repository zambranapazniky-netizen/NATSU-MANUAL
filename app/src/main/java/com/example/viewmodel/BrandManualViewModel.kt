package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ThemePreferencesRepository
import com.example.model.LogoBackdrop
import com.example.model.ManualBlock
import com.example.model.ManualUiState
import com.example.model.MockupAsset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrandManualViewModel(application: Application) : AndroidViewModel(application) {

    private val themePrefs = ThemePreferencesRepository(application.applicationContext)

    private val _uiState = MutableStateFlow(ManualUiState())
    val uiState: StateFlow<ManualUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            themePrefs.isDarkModeFlow.collect { isDark ->
                _uiState.update { it.copy(isDarkMode = isDark) }
            }
        }
    }

    fun toggleTheme() {
        val next = !_uiState.value.isDarkMode
        _uiState.update { it.copy(isDarkMode = next) }
        viewModelScope.launch {
            themePrefs.setDarkMode(next)
        }
    }

    fun selectBlock(block: ManualBlock) {
        _uiState.update { it.copy(selectedBlock = block) }
    }

    fun selectLogoBackdrop(backdrop: LogoBackdrop) {
        _uiState.update { it.copy(selectedBackdrop = backdrop) }
    }

    fun toggleConstructionGrid() {
        _uiState.update { it.copy(showConstructionGrid = !it.showConstructionGrid) }
    }

    fun toggleSafeZone() {
        _uiState.update { it.copy(showSafeZone = !it.showSafeZone) }
    }

    fun toggleKanjiLockup() {
        _uiState.update { it.copy(showKanjiLockup = !it.showKanjiLockup) }
    }

    fun updateFacadeSplit(fraction: Float) {
        _uiState.update { it.copy(facadeSplitFraction = fraction.coerceIn(0.02f, 0.98f)) }
    }

    fun openGallery(filter: ManualBlock? = null) {
        _uiState.update { it.copy(isGallerySheetOpen = true, galleryFilterBlock = filter) }
    }

    fun closeGallery() {
        _uiState.update { it.copy(isGallerySheetOpen = false) }
    }

    fun setGalleryFilter(filter: ManualBlock?) {
        _uiState.update { it.copy(galleryFilterBlock = filter) }
    }

    fun openZoomAsset(asset: MockupAsset) {
        _uiState.update { it.copy(zoomedAsset = asset) }
    }

    fun closeZoomAsset() {
        _uiState.update { it.copy(zoomedAsset = null) }
    }
}
