package com.filetools.util

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object HapticFeedback {
    
    fun lightClick(context: Context) {
        performHaptic(context, VibrationEffect.EFFECT_CLICK)
    }
    
    fun heavyClick(context: Context) {
        performHaptic(context, VibrationEffect.EFFECT_HEAVY_CLICK)
    }
    
    fun doubleClick(context: Context) {
        performHaptic(context, VibrationEffect.EFFECT_DOUBLE_CLICK)
    }
    
    fun tick(context: Context) {
        performHaptic(context, VibrationEffect.EFFECT_TICK)
    }
    
    private fun performHaptic(context: Context, effectType: Int) {
        val vibrator = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, effectType))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(50)
        }
    }
}
