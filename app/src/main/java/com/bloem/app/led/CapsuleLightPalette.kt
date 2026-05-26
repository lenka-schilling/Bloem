package com.bloem.app.led

import androidx.compose.ui.graphics.Color
import com.bloem.app.ui.theme.Eucalyptus
import com.bloem.app.ui.theme.Mist
import com.bloem.app.ui.theme.Moss
import com.bloem.app.ui.theme.Soot

data class CapsuleColorOption(val color: Color, val hasBorder: Boolean = false)

val capsuleColorOptions = listOf(
    CapsuleColorOption(Soot),
    CapsuleColorOption(Moss),
    CapsuleColorOption(Eucalyptus),
    CapsuleColorOption(Color.White, hasBorder = true),
    CapsuleColorOption(Mist),
    CapsuleColorOption(Color(0xFFB39DDB)),
    CapsuleColorOption(Color(0xFFEF9A9A)),
)
