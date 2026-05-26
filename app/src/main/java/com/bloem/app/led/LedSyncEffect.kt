package com.bloem.app.led

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun LedSyncEffect(
    color: Color,
    brightness: Float,
    lightsOn: Boolean,
) {
    val context = LocalContext.current.applicationContext
    LaunchedEffect(color, brightness, lightsOn) {
        delay(120)
        LedClient.setLight(
            context = context,
            color = color,
            brightness = brightness,
            on = lightsOn,
        )
    }
}
