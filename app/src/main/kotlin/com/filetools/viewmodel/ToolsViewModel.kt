package com.filetools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filetools.data.repository.ToolRepository
import com.filetools.data.local.ToolsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToolsViewModel(
    private val toolRepository: ToolRepository = ToolRepository()
) : ViewModel() {
    
    private val _featuredTools = MutableStateFlow<List<com.filetools.data.model.Tool>>(emptyList())
    val featuredTools: StateFlow<List<com.filetools.data.model.Tool>> = _featuredTools.asStateFlow()
    
    private val _favoriteTools = MutableStateFlow<Set<String>>(emptySet())
    val favoriteTools: StateFlow<Set<String>> = _favoriteTools.asStateFlow()
    
    private val _recentTools = MutableStateFlow<List<com.filetools.data.model.Tool>>(emptyList())
    val recentTools: StateFlow<List<com.filetools.data.model.Tool>> = _recentTools.asStateFlow()
    
    init {
        loadFeaturedTools()
    }
    
    private fun loadFeaturedTools() {
        viewModelScope.launch {
            try {
                val featured = listOf(
                    ToolsData.allTools.find { it.id == "pdf_to_word" },
                    ToolsData.allTools.find { it.id == "image_to_pdf" },
                    ToolsData.allTools.find { it.id == "pdf_compressor" },
                    ToolsData.allTools.find { it.id == "gif_maker" },
                    ToolsData.allTools.find { it.id == "zip_creator" }
                ).filterNotNull()
                
                _featuredTools.value = featured
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun toggleFavorite(toolId: String) {
        val currentFavorites = _favoriteTools.value.toMutableSet()
        if (currentFavorites.contains(toolId)) {
            currentFavorites.remove(toolId)
        } else {
            currentFavorites.add(toolId)
        }
        _favoriteTools.value = currentFavorites
    }
    
    fun addToRecent(tool: com.filetools.data.model.Tool) {
        val currentRecent = _recentTools.value.toMutableList()
        currentRecent.remove(tool)
        currentRecent.add(0, tool)
        
        // Keep only the last 10 recent tools
        if (currentRecent.size > 10) {
            currentRecent.removeAt(currentRecent.size - 1)
        }
        
        _recentTools.value = currentRecent
    }
    
    fun isFavorite(toolId: String): Boolean {
        return _favoriteTools.value.contains(toolId)
    }
    
    fun clearRecentTools() {
        _recentTools.value = emptyList()
    }
}
