package com.filetools.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filetools.ui.components.*
import com.filetools.ui.effects.ParticleEffect
import com.filetools.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToToolDetail: (String) -> Unit,
    viewModel: DashboardViewModel = viewModel()
) {
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Background particle effect
        ParticleEffect(
            modifier = Modifier.fillMaxSize(),
            particleColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Top App Bar
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "File Tools",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "All your file management needs",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                )
            )
            
            if (isLoading) {
                // Loading state
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(5) {
                        ShimmerEffect()
                    }
                }
            } else {
                // Categories
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(categories) { toolCategory ->
                        CategorySection(
                            toolCategory = toolCategory,
                            onToggleExpansion = { viewModel.toggleCategoryExpansion(toolCategory.category.id) },
                            onToolClick = onNavigateToToolDetail
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CategorySection(
    toolCategory: com.filetools.data.model.ToolCategory,
    onToggleExpansion: () -> Unit,
    onToolClick: (String) -> Unit
) {
    val category = toolCategory.category
    
    Column {
        CategoryCard(
            title = category.name,
            description = category.description,
            toolCount = category.tools.size,
            gradientStart = category.gradientStart,
            gradientEnd = category.gradientEnd,
            isExpanded = toolCategory.isExpanded,
            onToggle = onToggleExpansion,
            leadingContent = {
                Icon(
                    imageVector = getCategoryIcon(category.id),
                    contentDescription = category.name,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        
        if (toolCategory.isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(category.tools) { tool ->
                        ToolTile(
                            tool = tool,
                            gradientStart = category.gradientStart,
                            gradientEnd = category.gradientEnd,
                            onClick = { onToolClick(tool.id) }
                        )
                    }
                }
            )
            
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun getCategoryIcon(categoryId: String): ImageVector {
    return when (categoryId) {
        "convert_from_ebook", "convert_to_ebook" -> Icons.Default.Book
        "converter" -> Icons.Default.SwapHoriz
        "gif_tools" -> Icons.Default.Gif
        "zip_tools" -> Icons.Default.Archive
        "others" -> Icons.Default.MoreHoriz
        "optimize_images", "convert_images", "edit_images" -> Icons.Default.Image
        "optimize_pdf", "merge_split_pdf", "view_edit_pdf", 
        "convert_to_pdf", "convert_from_pdf", "pdf_security" -> Icons.Default.PictureAsPdf
        else -> Icons.Default.Build
    }
}
