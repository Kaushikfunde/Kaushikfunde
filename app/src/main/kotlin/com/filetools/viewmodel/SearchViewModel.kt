package com.filetools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filetools.data.repository.ToolRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val toolRepository: ToolRepository = ToolRepository()
) : ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<com.filetools.data.model.Tool>>(emptyList())
    val searchResults: StateFlow<List<com.filetools.data.model.Tool>> = _searchResults.asStateFlow()
    
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()
    
    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches: StateFlow<List<String>> = _recentSearches.asStateFlow()
    
    private var searchJob: Job? = null
    
    init {
        setupSearchFlow()
    }
    
    private fun setupSearchFlow() {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    performSearch(query)
                }
        }
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    private fun performSearch(query: String) {
        searchJob?.cancel()
        
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            _isSearching.value = false
            return
        }
        
        searchJob = viewModelScope.launch {
            _isSearching.value = true
            try {
                toolRepository.searchTools(query).collect { results ->
                    _searchResults.value = results
                }
                
                // Add to recent searches
                addToRecentSearches(query)
            } catch (e: Exception) {
                _searchResults.value = emptyList()
            } finally {
                _isSearching.value = false
            }
        }
    }
    
    private fun addToRecentSearches(query: String) {
        val currentRecent = _recentSearches.value.toMutableList()
        currentRecent.remove(query)
        currentRecent.add(0, query)
        
        // Keep only the last 5 searches
        if (currentRecent.size > 5) {
            currentRecent.removeAt(currentRecent.size - 1)
        }
        
        _recentSearches.value = currentRecent
    }
    
    fun clearRecentSearches() {
        _recentSearches.value = emptyList()
    }
    
    fun removeFromRecentSearches(query: String) {
        val currentRecent = _recentSearches.value.toMutableList()
        currentRecent.remove(query)
        _recentSearches.value = currentRecent
    }
    
    fun selectRecentSearch(query: String) {
        updateSearchQuery(query)
    }
    
    fun clearSearch() {
        updateSearchQuery("")
    }
}
