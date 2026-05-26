package com.bloem.app.led

import android.os.Build

fun isRunningOnEmulator(): Boolean {
    return Build.FINGERPRINT.startsWith("generic")
        || Build.FINGERPRINT.startsWith("unknown")
        || Build.MODEL.contains("google_sdk", ignoreCase = true)
        || Build.MODEL.contains("Emulator", ignoreCase = true)
        || Build.MODEL.contains("Android SDK built for", ignoreCase = true)
        || Build.MANUFACTURER.contains("Genymotion", ignoreCase = true)
        || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        || Build.PRODUCT.contains("sdk", ignoreCase = true)
}
