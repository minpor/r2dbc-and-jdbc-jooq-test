package ru.jooq.test.jooqtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqTestApplication

fun main(args: Array<String>) {
    runApplication<JooqTestApplication>(*args)
}
