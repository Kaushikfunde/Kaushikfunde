package com.filetools.ui.effects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ShaderBrushEffect(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color(0xFF667EEA),
        Color(0xFF764BA2),
        Color(0xFFF093FB)
    ),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = colors,
                    radius = 1000f
                )
            )
    ) {
        content()
    }
}
