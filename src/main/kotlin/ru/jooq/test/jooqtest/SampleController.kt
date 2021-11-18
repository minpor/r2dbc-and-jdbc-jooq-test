package ru.jooq.test.jooqtest

import java.time.LocalDateTime
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class SampleController(private val sampleService: SampleService) {


    @GetMapping("/sample/jooq-reactive", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun jooqReactive(@RequestParam limit: Long): Flux<ResultDto> {
        return sampleService.getReactiveJooq(LocalDateTime.now(), limit)
    }

    @GetMapping("/sample/jooq-blocking")
    fun jooqBlocking(@RequestParam limit: Long): List<ResultDto> {
        return sampleService.getBlockingJooq(LocalDateTime.now(), limit)
    }

}
