package com.bloem.app.data

import java.time.LocalDateTime

data class Booking(
    val startTime: LocalDateTime,
    val durationMinutes: Int,
    val userId: String = "default_user" // Placeholder for now
) {
    val endTime: LocalDateTime
        get() = startTime.plusMinutes(durationMinutes.toLong())
}
