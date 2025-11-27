package com.filetools.data.repository

import com.filetools.data.model.AppSettings
import com.filetools.data.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepository {
    
    private val _settings = MutableStateFlow(
        AppSettings(
            theme = ThemeMode.SYSTEM,
            language = "en",
            isOnboardingCompleted = false,
            hapticFeedbackEnabled = true
        )
    )
    
    val settings: Flow<AppSettings> = _settings.asStateFlow()
    
    suspend fun updateTheme(theme: ThemeMode) {
        _settings.value = _settings.value.copy(theme = theme)
    }
    
    suspend fun updateLanguage(language: String) {
        _settings.value = _settings.value.copy(language = language)
    }
    
    suspend fun setOnboardingCompleted(completed: Boolean) {
        _settings.value = _settings.value.copy(isOnboardingCompleted = completed)
    }
    
    suspend fun updateHapticFeedback(enabled: Boolean) {
        _settings.value = _settings.value.copy(hapticFeedbackEnabled = enabled)
    }
}
