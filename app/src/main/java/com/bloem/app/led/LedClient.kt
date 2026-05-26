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

        val r = (color.red * 255 * scale).roundToInt().coerceIn(0, 255)
        val g = (color.green * 255 * scale).roundToInt().coerceIn(0, 255)
        val b = (color.blue * 255 * scale).roundToInt().coerceIn(0, 255)

        return "RGB:$r,$g,$b\n"
    }
}
