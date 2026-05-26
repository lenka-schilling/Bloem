package com.bloem.app.data

import java.util.Date

data class Session(
    val id: String = "",
    val userId: String = "", // Will be used for user login later
    val startTime: Date = Date(),
    val durationMinutes: Int = 0
)
