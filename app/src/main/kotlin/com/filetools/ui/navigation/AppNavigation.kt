package com.filetools.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filetools.ui.screens.DashboardScreen
import com.filetools.ui.screens.OnboardingScreen
import com.filetools.ui.screens.SearchScreen
import com.filetools.ui.screens.SettingsScreen
import com.filetools.ui.screens.SplashScreen
import com.filetools.ui.screens.ToolDetailScreen
import com.filetools.util.Constants
import com.filetools.viewmodel.SettingsViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    settingsViewModel: SettingsViewModel
) {
    val settings by settingsViewModel.settings.collectAsState()
    val startDestination = if (settings.isOnboardingCompleted) {
        Constants.DASHBOARD_SCREEN
    } else {
        Constants.SPLASH_SCREEN
    }
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Constants.SPLASH_SCREEN) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Constants.ONBOARDING_SCREEN) {
                        popUpTo(Constants.SPLASH_SCREEN) { inclusive = true }
                    }
                },
                onNavigateToDashboard = {
                    navController.navigate(Constants.DASHBOARD_SCREEN) {
                        popUpTo(Constants.SPLASH_SCREEN) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Constants.ONBOARDING_SCREEN) {
            OnboardingScreen(
                onGetStarted = {
                    settingsViewModel.setOnboardingCompleted()
                    navController.navigate(Constants.DASHBOARD_SCREEN) {
                        popUpTo(Constants.ONBOARDING_SCREEN) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Constants.DASHBOARD_SCREEN) {
            DashboardScreen(
                onNavigateToSearch = {
                    navController.navigate(Constants.SEARCH_SCREEN)
                },
                onNavigateToSettings = {
                    navController.navigate(Constants.SETTINGS_SCREEN)
                },
                onNavigateToToolDetail = { toolId ->
                    navController.navigate("${Constants.TOOL_DETAIL_SCREEN}/$toolId")
                }
            )
        }
        
        composable(Constants.SEARCH_SCREEN) {
            SearchScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToToolDetail = { toolId ->
                    navController.navigate("${Constants.TOOL_DETAIL_SCREEN}/$toolId")
                }
            )
        }
        
        composable(Constants.SETTINGS_SCREEN) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("${Constants.TOOL_DETAIL_SCREEN}/{toolId}") { backStackEntry ->
            val toolId = backStackEntry.arguments?.getString("toolId") ?: ""
            ToolDetailScreen(
                toolId = toolId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
