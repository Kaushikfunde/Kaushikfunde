package com.filetools.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.filetools.theme.FileToolsTheme
import com.filetools.util.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToDashboard: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseOutCubic
        ), label = "scale"
    )
    
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = EaseOutCubic
        ), label = "alpha"
    )
    
    LaunchedEffect(Unit) {
        delay(Constants.SPLASH_DELAY)
        // For now, navigate directly to dashboard
        // In a real app, you might check if onboarding is completed
        onNavigateToDashboard()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF667EEA),
                        Color(0xFF764BA2)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale),
                tint = Color.White
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "File Tools",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = alpha),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Your All-in-One File Solution",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = alpha * 0.8f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        }
    }
}
