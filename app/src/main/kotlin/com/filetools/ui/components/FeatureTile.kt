package com.filetools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.filetools.util.createGradientBrush

@Composable
fun FeatureTile(
    title: String,
    description: String,
    icon: ImageVector,
    gradientStart: String,
    gradientEnd: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPro: Boolean = false,
    isNew: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            createGradientBrush(gradientStart, gradientEnd).let { brush ->
                                // Extract color from gradient for background
                                Color.Transparent
                            },
                            Color.Transparent
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(createGradientBrush(gradientStart, gradientEnd)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                
                if (isPro || isNew) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isPro) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Pro",
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                        if (isNew) {
                            Icon(
                                imageVector = Icons.Default.NewReleases,
                                contentDescription = "New",
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToolTile(
    tool: com.filetools.data.model.Tool,
    gradientStart: String,
    gradientEnd: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Map tool icon names to Material Icons
    val icon = when (tool.icon) {
        "epub", "mobi", "azw", "fb2", "lit", "pdb" -> Icons.Default.Book
        "word" -> Icons.Default.Description
        "excel" -> Icons.Default.TableChart
        "powerpoint" -> Icons.Default.Slideshow
        "image", "jpg", "png", "webp", "jpeg", "gif", "svg", "tiff", "bmp" -> Icons.Default.Image
        "pdf" -> Icons.Default.PictureAsPdf
        "zip" -> Icons.Default.Archive
        "rename" -> Icons.Default.Edit
        "duplicate" -> Icons.Default.ContentCopy
        "compare" -> Icons.Default.Compare
        "metadata" -> Icons.Default.Info
        "crop" -> Icons.Default.Crop
        "resize" -> Icons.Default.AspectRatio
        "rotate" -> Icons.Default.RotateRight
        "flip" -> Icons.Default.Flip
        "watermark" -> Icons.Default.Watermark
        "background" -> Icons.Default.LayersClear
        "compress" -> Icons.Default.Compress
        "merge" -> Icons.Default.MergeType
        "split" -> Icons.Default.CallSplit
        "extract" -> Icons.Default.FileDownload
        "view" -> Icons.Default.Visibility
        "edit" -> Icons.Default.Edit
        "annotate" -> Icons.Default.Comment
        "forms" -> Icons.Default.Assignment
        "signature" -> Icons.Default.Draw
        "password" -> Icons.Default.Lock
        "unlock" -> Icons.Default.LockOpen
        "organize" -> Icons.Default.Reorder
        "html" -> Icons.Default.Code
        "text" -> Icons.Default.TextFields
        "encrypt" -> Icons.Default.EnhancedEncryption
        "decrypt" -> Icons.Default.NoEncryption
        else -> Icons.Default.Build
    }
    
    FeatureTile(
        title = tool.name,
        description = tool.description,
        icon = icon,
        gradientStart = gradientStart,
        gradientEnd = gradientEnd,
        onClick = onClick,
        modifier = modifier,
        isPro = tool.isPro,
        isNew = tool.isNew
    )
}
