package com.bloem.app.data

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object BookingManager {
    private val bookings = mutableListOf<Booking>()
    private const val VENTILATION_BUFFER_MINUTES = 5L

    fun addBooking(booking: Booking): Boolean {
        if (isSlotAvailable(booking.startTime, booking.durationMinutes)) {
            bookings.add(booking)
            return true
        }
        return false
    }

    fun getBookings(): List<Booking> {
        return bookings.toList()
    }

    fun isSlotAvailable(proposedStartTime: LocalDateTime, proposedDurationMinutes: Int): Boolean {
        val proposedEndTime = proposedStartTime.plusMinutes(proposedDurationMinutes.toLong())

        for (existingBooking in bookings) {
            val existingBookingStart = existingBooking.startTime
            val existingBookingEnd = existingBooking.endTime

            // Check for overlap, considering the ventilation buffer
            val bufferedExistingBookingStart = existingBookingStart.minusMinutes(VENTILATION_BUFFER_MINUTES)
            val bufferedExistingBookingEnd = existingBookingEnd.plusMinutes(VENTILATION_BUFFER_MINUTES)

            val overlap = !(proposedEndTime <= bufferedExistingBookingStart || proposedStartTime >= bufferedExistingBookingEnd)

            if (overlap) {
                return false
            }
        }
        return true
    }

    fun getAvailableTimeSlots(date: LocalDateTime, sessionDurations: List<Int>, intervalMinutes: Int = 30): List<LocalDateTime> {
        val availableSlots = mutableListOf<LocalDateTime>()
        var currentTime = date.truncatedTo(ChronoUnit.DAYS).plusHours(8) // Start from 8 AM
        val endOfDay = date.truncatedTo(ChronoUnit.DAYS).plusHours(22) // End at 10 PM

        while (currentTime.plusMinutes(sessionDurations.minOrNull()?.toLong() ?: 0) <= endOfDay) {
            var isCurrentSlotAvailable = false
            for (duration in sessionDurations) {
                if (isSlotAvailable(currentTime, duration)) {
                    isCurrentSlotAvailable = true
                    break
                }
            }
            if (isCurrentSlotAvailable) {
                availableSlots.add(currentTime)
            }
            currentTime = currentTime.plusMinutes(intervalMinutes.toLong())
        }
        return availableSlots
    }

    fun isBooked(time: LocalDateTime, duration: Int): Boolean {
        return !isSlotAvailable(time, duration)
    }
}
