package com.filetools.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filetools.data.model.ThemeMode
import com.filetools.ui.components.*
import com.filetools.util.Constants
import com.filetools.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = viewModel()
) {
    val settings by viewModel.settings.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
            )
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Theme Section
            item {
                SettingsSection(title = "Appearance") {
                    ThemeSelector(
                        currentTheme = settings.theme,
                        onThemeSelected = viewModel::updateTheme
                    )
                }
            }
            
            // Language Section
            item {
                SettingsSection(title = "Language") {
                    LanguageSelector(
                        currentLanguage = settings.language,
                        onLanguageSelected = viewModel::updateLanguage
                    )
                }
            }
            
            // Preferences Section
            item {
                SettingsSection(title = "Preferences") {
                    HapticFeedbackToggle(
                        enabled = settings.hapticFeedbackEnabled,
                        onToggle = viewModel::toggleHapticFeedback
                    )
                }
            }
            
            // About Section
            item {
                SettingsSection(title = "About") {
                    AboutSection()
                }
            }
            
            // Support Section
            item {
                SettingsSection(title = "Support") {
                    SupportSection()
                }
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun ThemeSelector(
    currentTheme: ThemeMode,
    onThemeSelected: (ThemeMode) -> Unit
) {
    Column(modifier = Modifier.selectableGroup()) {
        ThemeOption(
            text = "Light",
            selected = currentTheme == ThemeMode.LIGHT,
            onClick = { onThemeSelected(ThemeMode.LIGHT) }
        )
        ThemeOption(
            text = "Dark",
            selected = currentTheme == ThemeMode.DARK,
            onClick = { onThemeSelected(ThemeMode.DARK) }
        )
        ThemeOption(
            text = "System",
            selected = currentTheme == ThemeMode.SYSTEM,
            onClick = { onThemeSelected(ThemeMode.SYSTEM) }
        )
    }
}

@Composable
private fun ThemeOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun LanguageSelector(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    val languages = mapOf(
        Constants.LANG_ENGLISH to "English",
        Constants.LANG_SPANISH to "Español",
        Constants.LANG_FRENCH to "Français",
        Constants.LANG_GERMAN to "Deutsch"
    )
    
    Column(modifier = Modifier.selectableGroup()) {
        languages.forEach { (code, name) ->
            LanguageOption(
                text = name,
                selected = currentLanguage == code,
                onClick = { onLanguageSelected(code) }
            )
        }
    }
}

@Composable
private fun LanguageOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun HapticFeedbackToggle(
    enabled: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Haptic Feedback",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Enable vibration for interactions",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        
        Switch(
            checked = enabled,
            onCheckedChange = { onToggle() }
        )
    }
}

@Composable
private fun AboutSection() {
    Column {
        SettingsItem(
            icon = Icons.Default.Info,
            title = "Version",
            subtitle = "1.0.0",
            onClick = { }
        )
        SettingsItem(
            icon = Icons.Default.PrivacyTip,
            title = "Privacy Policy",
            onClick = { }
        )
        SettingsItem(
            icon = Icons.Default.Description,
            title = "Terms of Service",
            onClick = { }
        )
    }
}

@Composable
private fun SupportSection() {
    Column {
        SettingsItem(
            icon = Icons.Default.Feedback,
            title = "Send Feedback",
            onClick = { }
        )
        SettingsItem(
            icon = Icons.Default.ContactSupport,
            title = "Contact Support",
            onClick = { }
        )
        SettingsItem(
            icon = Icons.Default.ClearAll,
            title = "Clear Cache",
            onClick = { }
        )
    }
}

@Composable
private fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            subtitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
        
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}
