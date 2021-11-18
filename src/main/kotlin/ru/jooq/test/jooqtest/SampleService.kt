package ru.jooq.test.jooqtest

import java.time.LocalDateTime
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class SampleService(private val sampleRepository: SampleRepository) {

    fun getReactiveJooq(queryTime: LocalDateTime, limit: Long): Flux<ResultDto> {
        return sampleRepository.getReactiveJooq(queryTime, limit)
    }

    fun getBlockingJooq(queryTime: LocalDateTime, limit: Long): List<ResultDto> {
        return sampleRepository.getBlockingJooq(queryTime, limit).also {
            println("List size ${it.size}")
        }
    }
}