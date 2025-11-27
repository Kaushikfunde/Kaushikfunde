package com.filetools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filetools.data.model.ToolCategory
import com.filetools.data.repository.ToolRepository
import com.filetools.data.local.ToolsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val toolRepository: ToolRepository = ToolRepository()
) : ViewModel() {
    
    private val _categories = MutableStateFlow<List<ToolCategory>>(emptyList())
    val categories: StateFlow<List<ToolCategory>> = _categories.asStateFlow()
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        loadCategories()
    }
    
    private fun loadCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val toolCategories = ToolsData.categories.map { category ->
                    ToolCategory(category = category, isExpanded = false)
                }
                _categories.value = toolCategories
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun toggleCategoryExpansion(categoryId: String) {
        val updatedCategories = _categories.value.map { toolCategory ->
            if (toolCategory.category.id == categoryId) {
                toolCategory.copy(isExpanded = !toolCategory.isExpanded)
            } else {
                toolCategory
            }
        }
        _categories.value = updatedCategories
    }
    
    fun expandAllCategories() {
        val updatedCategories = _categories.value.map { toolCategory ->
            toolCategory.copy(isExpanded = true)
        }
        _categories.value = updatedCategories
    }
    
    fun collapseAllCategories() {
        val updatedCategories = _categories.value.map { toolCategory ->
            toolCategory.copy(isExpanded = false)
        }
        _categories.value = updatedCategories
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun refresh() {
        loadCategories()
    }
}
