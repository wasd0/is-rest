package com.wasd.isrest.utils

import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

@Component
class DateTimeHolder(private val clock: Clock = Clock.systemUTC()) {
    fun now() = ZonedDateTime.now(clock)
    fun dayStart() = LocalDate.now().atStartOfDay(clock.zone)
    fun dayEnd() = LocalDate.now().atTime(LocalTime.MAX).atZone(clock.zone)
    fun dayStart(startDate: LocalDate) = startDate.atStartOfDay(clock.zone)
    fun dayEnd(endDate: LocalDate) = endDate.atTime(LocalTime.MAX).atZone(clock.zone)
}