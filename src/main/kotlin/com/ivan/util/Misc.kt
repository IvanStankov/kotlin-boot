package com.ivan.util

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ThreadLocalRandom

fun randomDateTime(): LocalDateTime {
    val minDay = LocalDateTime.of(1970, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)
    val maxDay = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
    return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC)
}

