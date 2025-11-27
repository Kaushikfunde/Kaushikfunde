package com.filetools.data.repository

import com.filetools.data.local.ToolsData
import com.filetools.data.model.Tool
import com.filetools.data.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ToolRepository {
    
    fun getAllCategories(): Flow<List<Category>> {
        return flowOf(ToolsData.categories)
    }
    
    fun getCategoryById(categoryId: String): Flow<Category?> {
        return flowOf(ToolsData.categories.find { it.id == categoryId })
    }
    
    fun getAllTools(): Flow<List<Tool>> {
        return flowOf(ToolsData.allTools)
    }
    
    fun getToolsByCategory(categoryId: String): Flow<List<Tool>> {
        return flowOf(ToolsData.allTools.filter { it.categoryId == categoryId })
    }
    
    fun searchTools(query: String): Flow<List<Tool>> {
        val filteredTools = if (query.isBlank()) {
            emptyList()
        } else {
            ToolsData.allTools.filter { tool ->
                tool.name.contains(query, ignoreCase = true) ||
                tool.description.contains(query, ignoreCase = true)
            }
        }
        return flowOf(filteredTools)
    }
    
    fun getToolById(toolId: String): Flow<Tool?> {
        return flowOf(ToolsData.allTools.find { it.id == toolId })
    }
}
