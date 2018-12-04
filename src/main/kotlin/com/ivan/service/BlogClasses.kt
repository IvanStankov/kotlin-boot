package com.ivan.service

import com.fasterxml.jackson.annotation.JsonFormat
import com.ivan.DateRange
import java.time.LocalDateTime

data class Blog(
    val id: Long,
    val name: String,
    val description: String,
    @JsonFormat(pattern = "yyyyMMdd'T'HHmmss")
    val creationDate: LocalDateTime
)

inline class BlogId(val id: Long)

data class BlogFilter(
    val title: String?,
    val creationDate: DateRange?) {

    companion object {
        private val EMPTY_FILTER = BlogFilter(null, null)
        fun empty(): BlogFilter = EMPTY_FILTER
    }
}
