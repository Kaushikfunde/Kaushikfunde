package com.filetools.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class OnboardingPage(
    title: String,
    description: String,
    icon: ImageVector,
    gradientColors: List<Color>
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            title = "Convert Files",
            description = "Convert between different file formats seamlessly with our powerful conversion tools",
            icon = Icons.Default.SwapHoriz,
            gradientColors = listOf(Color(0xFF667EEA), Color(0xFF764BA2))
        ),
        OnboardingPage(
            title = "Edit Documents",
            description = "Edit and enhance your documents and images with professional tools",
            icon = Icons.Default.Edit,
            gradientColors = listOf(Color(0xFFF093FB), Color(0xFFF5576C))
        ),
        OnboardingPage(
            title = "Optimize & Secure",
            description = "Compress files and protect them with encryption and password security",
            icon = Icons.Default.Security,
            gradientColors = listOf(Color(0xFF4FACFE), Color(0xFF00F2FE))
        )
    )
    
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    
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
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Skip button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onGetStarted
                ) {
                    Text(
                        text = "Skip",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            // Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPageContent(pages[page])
            }
            
            // Page indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                color = if (index == pagerState.currentPage) {
                                    Color.White
                                } else {
                                    Color.White.copy(alpha = 0.4f)
                                }
                            )
                    )
                    if (index < pages.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            
            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pagerState.currentPage > 0) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    ) {
                        Text(
                            text = "Previous",
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(80.dp))
                }
                
                Button(
                    onClick = {
                        if (pagerState.currentPage < pages.size - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onGetStarted()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF667EEA)
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier.height(56.dp)
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(
                    Brush.radialGradient(
                        colors = page.gradientColors
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.White
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = page.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.9f),
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
    }
}
