package com.filetools.ui.effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.*
import kotlin.random.Random

@Composable
fun ParticleEffect(
    modifier: Modifier = Modifier,
    particleCount: Int = 50,
    particleColor: Color = Color.White.copy(alpha = 0.5f)
) {
    val particles = remember {
        List(particleCount) {
            Particle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                vx = Random.nextFloat() * 0.002f - 0.001f,
                vy = Random.nextFloat() * 0.002f - 0.001f,
                radius = Random.nextFloat() * 3f + 1f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "particle")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "time"
    )

    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        particles.forEach { particle ->
            val x = particle.x + particle.vx * time * 1000
            val y = particle.y + particle.vy * time * 1000
            
            val wrappedX = x.rem(1f).let { if (it < 0) it + 1f else it }
            val wrappedY = y.rem(1f).let { if (it < 0) it + 1f else it }
            
            drawCircle(
                color = particleColor,
                radius = particle.radius,
                center = Offset(
                    x = wrappedX * size.width,
                    y = wrappedY * size.height
                )
            )
        }
    }
}

private data class Particle(
    val x: Float,
    val y: Float,
    val vx: Float,
    val vy: Float,
    val radius: Float
)
