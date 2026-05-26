package com.bloem.app.led

/**
 * ESP32 TCP settings (same as the EPS SocketClient: port 2000, RGB: / LED: commands).
 *
 * **Emulator:** app connects to 10.0.2.2:[EMULATOR_PROXY_PORT] → run [tools/led_proxy.py] on your PC.
 * **Real Android phone:** set [ESP32_HOST] to the IP from Arduino Serial Monitor, same Wi‑Fi/hotspot.
 */
object LedConfig {
    /** ESP32 IP from Serial Monitor (e.g. 172.20.10.3 on an iPhone hotspot). */
    const val ESP32_HOST = "172.20.10.3"

    const val ESP32_PORT = 2000

    /** Emulator → your PC. Do not change. */
    const val EMULATOR_HOST = "10.0.2.2"

    /** PC proxy port — must match [tools/led_proxy.py]. */
    const val EMULATOR_PROXY_PORT = 8888

    fun host(forEmulator: Boolean): String =
        if (forEmulator) EMULATOR_HOST else ESP32_HOST

    fun port(forEmulator: Boolean): Int =
        if (forEmulator) EMULATOR_PROXY_PORT else ESP32_PORT
}
