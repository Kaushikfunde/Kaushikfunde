package com.filetools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filetools.data.model.AppSettings
import com.filetools.data.model.ThemeMode
import com.filetools.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository = SettingsRepository()
) : ViewModel() {
    
    private val _settings = MutableStateFlow(
        AppSettings(
            theme = ThemeMode.SYSTEM,
            language = "en",
            isOnboardingCompleted = false,
            hapticFeedbackEnabled = true
        )
    )
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()
    
    init {
        viewModelScope.launch {
            settingsRepository.settings.collect { appSettings ->
                _settings.value = appSettings
            }
        }
    }
    
    fun updateTheme(theme: ThemeMode) {
        viewModelScope.launch {
            settingsRepository.updateTheme(theme)
        }
    }
    
    fun updateLanguage(language: String) {
        viewModelScope.launch {
            settingsRepository.updateLanguage(language)
        }
    }
    
    fun toggleHapticFeedback() {
        viewModelScope.launch {
            settingsRepository.updateHapticFeedback(!_settings.value.hapticFeedbackEnabled)
        }
    }
    
    fun setOnboardingCompleted() {
        viewModelScope.launch {
            settingsRepository.setOnboardingCompleted(true)
        }
    }
}
