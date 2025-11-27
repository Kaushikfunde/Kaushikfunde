package com.filetools.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun createGradientBrush(
    startColor: String,
    endColor: String
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            Color(android.graphics.Color.parseColor(startColor)),
            Color(android.graphics.Color.parseColor(endColor))
        )
    )
}

fun Modifier.glassEffect(): Modifier = this
    .clip(RoundedCornerShape(20.dp))
    .background(
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
    )

fun Modifier.categoryCard(): Modifier = this
    .fillMaxWidth()
    .clip(RoundedCornerShape(20.dp))

fun Modifier.featureTile(): Modifier = this
    .clip(RoundedCornerShape(16.dp))

fun Color.Companion.fromHex(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}
