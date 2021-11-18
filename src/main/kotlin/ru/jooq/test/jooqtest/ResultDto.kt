package ru.jooq.test.jooqtest

import java.time.Duration
import java.time.LocalDateTime

class ResultDto(
    val queryTime: LocalDateTime,
    val dataTime: LocalDateTime,
    timeToResult: Long? = 0,
    val dataDto: DataDto
) {
    val timeToResult: Long = Duration.between(queryTime, dataTime).toMillis()
}