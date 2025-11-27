package com.filetools.data.model

data class Tool(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val icon: String,
    val isPro: Boolean = false,
    val isNew: Boolean = false
)

data class Category(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val gradientStart: String,
    val gradientEnd: String,
    val tools: List<Tool>
)

data class ToolCategory(
    val category: Category,
    val isExpanded: Boolean = false
)

data class AppSettings(
    val theme: ThemeMode = ThemeMode.SYSTEM,
    val language: String = "en",
    val isOnboardingCompleted: Boolean = false,
    val hapticFeedbackEnabled: Boolean = true
)

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}
