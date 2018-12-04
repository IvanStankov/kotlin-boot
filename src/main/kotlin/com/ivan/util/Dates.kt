package com.ivan.util

import java.time.LocalDateTime

fun LocalDateTime.isBeforeOrEqual(other: LocalDateTime) = this.isBefore(other) || this.equals(other)
fun LocalDateTime.isAfterOrEqual(other: LocalDateTime) = this.isAfter(other) || this.equals(other)