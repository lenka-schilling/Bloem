package com.bloem.app.led

import androidx.compose.ui.graphics.Color

data class CapsuleColorOption(val color: Color, val hasBorder: Boolean = false)

/**
 * Strong, distinct "LED-friendly" colors.
 *
 * These are chosen to map cleanly to RGB output so the physical LEDs match
 * what you see on screen far more closely than muted/grey theme colors.
 */
val capsuleColorOptions = listOf(
    CapsuleColorOption(Color.White, hasBorder = true),
    CapsuleColorOption(Color(0xFFFF0000)), // Red
    CapsuleColorOption(Color(0xFF00FF00)), // Green
    CapsuleColorOption(Color(0xFF0000FF)), // Blue
    CapsuleColorOption(Color(0xFFFFFF00)), // Yellow
    CapsuleColorOption(Color(0xFF00FFFF)), // Cyan
    CapsuleColorOption(Color(0xFFFF00FF)), // Magenta / Purple-ish
    CapsuleColorOption(Color(0xFFFF8000)), // Orange
)
