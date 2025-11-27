package com.filetools.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.filetools.ui.components.CustomButton
import com.filetools.viewmodel.ToolsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolDetailScreen(
    toolId: String,
    onNavigateBack: () -> Unit,
    viewModel: ToolsViewModel = viewModel()
) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 300,
            easing = EaseOutCubic
        ), label = "scale"
    )
    
    // Mock tool data - in a real app, you'd get this from the repository
    val tool = remember(toolId) {
        com.filetools.data.model.Tool(
            id = toolId,
            name = "Tool Name",
            description = "Tool description goes here",
            categoryId = "converter",
            icon = "pdf"
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text(tool.name) },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { viewModel.toggleFavorite(tool.id) }
                ) {
                    Icon(
                        imageVector = if (viewModel.isFavorite(tool.id)) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "Favorite",
                        tint = if (viewModel.isFavorite(tool.id)) {
                            Color.Red
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
            )
        )
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Tool Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getToolIcon(tool.icon),
                    contentDescription = tool.name,
                    modifier = Modifier.size(60.dp),
                    tint = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Tool Name
            Text(
                text = tool.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Tool Description
            Text(
                text = tool.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Features
            Text(
                text = "Features",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            FeatureList()
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Action Button
            CustomButton(
                text = "Start ${tool.name}",
                icon = Icons.Default.PlayArrow,
                onClick = {
                    // Handle tool action
                    viewModel.addToRecent(tool)
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Related Tools
            Text(
                text = "Related Tools",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            RelatedToolsSection()
        }
    }
}

@Composable
private fun FeatureList() {
    val features = listOf(
        "Fast and efficient processing",
        "High-quality output",
        "Support for multiple formats",
        "User-friendly interface",
        "No internet connection required"
    )
    
    features.forEach { feature ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = feature,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun RelatedToolsSection() {
    val relatedTools = listOf(
        "PDF to Word",
        "Word to PDF",
        "PDF Compressor",
        "Image to PDF"
    )
    
    relatedTools.forEach { toolName ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = toolName,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
private fun getToolIcon(iconName: String): ImageVector {
    return when (iconName) {
        "pdf" -> Icons.Default.PictureAsPdf
        "word" -> Icons.Default.Description
        "excel" -> Icons.Default.TableChart
        "powerpoint" -> Icons.Default.Slideshow
        "image" -> Icons.Default.Image
        "zip" -> Icons.Default.Archive
        "gif" -> Icons.Default.Gif
        else -> Icons.Default.Build
    }
}
