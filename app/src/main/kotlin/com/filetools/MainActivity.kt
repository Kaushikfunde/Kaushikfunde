package com.filetools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.filetools.ui.navigation.AppNavigation
import com.filetools.ui.screens.SplashScreen
import com.filetools.theme.FileToolsTheme
import com.filetools.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FileToolsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val settingsViewModel: SettingsViewModel = viewModel()
                    val navController = rememberNavController()
                    
                    AppNavigation(
                        navController = navController,
                        settingsViewModel = settingsViewModel
                    )
                }
            }
        }
    }
}
