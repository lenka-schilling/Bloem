package com.bloem.app.led

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.math.roundToInt

object LedClient {
    private const val TAG = "LedClient"

    suspend fun setLight(
        context: Context,
        color: Color,
        brightness: Float,
        on: Boolean,
    ): Boolean = withContext(Dispatchers.IO) {
        val emulator = isRunningOnEmulator()
        val host = LedConfig.host(forEmulator = emulator)
        val port = LedConfig.port(forEmulator = emulator)
        val message = buildCommand(color, brightness, on)

        try {
            Socket().use { socket ->
                socket.connect(InetSocketAddress(host, port), 3000)
                socket.soTimeout = 3000

                OutputStreamWriter(socket.getOutputStream(), Charsets.UTF_8).use { out ->
                    out.write(message)
                    out.flush()
                }

                val response = BufferedReader(
                    InputStreamReader(socket.getInputStream(), Charsets.UTF_8)
                ).readLine()

                val ok = response?.contains("ACK", ignoreCase = true) == true
                if (ok) {
                    Log.d(TAG, "LED OK ($host:$port): ${message.trim()}")
                } else {
                    Log.w(TAG, "Unexpected response from ESP32: $response")
                }
                ok
            }
        } catch (e: Exception) {
            val hint = if (emulator) {
                "Start tools/led_proxy.py on your PC first."
            } else {
                "Check ESP32 IP in LedConfig.kt (${LedConfig.ESP32_HOST}) and port ${LedConfig.ESP32_PORT}."
            }
            Log.e(TAG, "LED TCP failed ($host:$port): ${e.message}. $hint")
            false
        }
    }

    /** Same format as the EPS SocketClient: RGB:r,g,b or LED:0 */
    internal fun buildCommand(color: Color, brightness: Float, on: Boolean): String {
        if (!on) return "LED:0\n"

        val scale = brightness.coerceIn(0f, 1f)
        if (scale <= 0f) return "LED:0\n"

        // Convert UI color → LED color with extra contrast.
        // Many of the UI theme colors are intentionally muted/grey.
        // On real RGB strips those can look very similar, especially with blue-heavy LEDs.
        val (r0, g0, b0) = toRgb255(color)
        val (rb, gb, bb) = boostSaturation(r0, g0, b0)

        val r = (rb * scale * LedConfig.RED_GAIN).roundToInt().coerceIn(0, 255)
        val g = (gb * scale * LedConfig.GREEN_GAIN).roundToInt().coerceIn(0, 255)
        val b = (bb * scale * LedConfig.BLUE_GAIN).roundToInt().coerceIn(0, 255)

        return "RGB:$r,$g,$b\n"
    }

    private fun toRgb255(color: Color): Triple<Int, Int, Int> {
        val r = (color.red * 255f).roundToInt().coerceIn(0, 255)
        val g = (color.green * 255f).roundToInt().coerceIn(0, 255)
        val b = (color.blue * 255f).roundToInt().coerceIn(0, 255)
        return Triple(r, g, b)
    }

    /**
     * Expand the channel range to make muted colors more distinct on LEDs.
     * Preserves hue while increasing saturation:
     * - subtract min channel (removes grey component)
     * - scale so the max channel hits 255
     */
    private fun boostSaturation(r: Int, g: Int, b: Int): Triple<Float, Float, Float> {
        val minC = minOf(r, g, b)
        val maxC = maxOf(r, g, b)
        val range = maxC - minC

        // Very grey colors: keep them grey (but keep them visible).
        if (range <= 10) {
            val v = maxC.toFloat()
            return Triple(v, v, v)
        }

        val rf = (r - minC).toFloat()
        val gf = (g - minC).toFloat()
        val bf = (b - minC).toFloat()
        val scale = 255f / range.toFloat()
        return Triple(rf * scale, gf * scale, bf * scale)
    }
}
