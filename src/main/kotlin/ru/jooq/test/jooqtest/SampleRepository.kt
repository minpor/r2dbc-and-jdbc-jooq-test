package ru.jooq.test.jooqtest

import java.time.LocalDateTime
import org.jooq.DSLContext
import org.jooq.Record8
import org.jooq.SelectLimitPercentStep
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import ru.jooq.test.jooqtest.domain.Tables

@Repository
class SampleRepository(
    @Qualifier("r2dbcDSLContext")
    private val r2dbcDSLContext: DSLContext,
    @Qualifier("jdbcDSLContext")
    private val jdbcDSLContext: DSLContext
) {

    private val d = Tables.DOMA
    private val s = Tables.STREET
    private val f = Tables.FLAT

    fun getReactiveJooq(queryTime: LocalDateTime, limit: Long): Flux<ResultDto> {
        return Flux.from(getQuery(r2dbcDSLContext, limit))
            .map { r ->
                ResultDto(
                    queryTime = queryTime,
                    dataTime = LocalDateTime.now(),
                    dataDto = r.into(d).into(DataDto::class.java)
                )
            }
    }

    fun getBlockingJooq(queryTime: LocalDateTime, limit: Long): List<ResultDto> {
        return getQuery(jdbcDSLContext, limit)
            .map { r ->
                ResultDto(
                    queryTime = queryTime,
                    dataTime = LocalDateTime.now(),
                    dataDto = r.into(d).into(DataDto::class.java)
                )
            }
    }

    private fun getQuery(dslContext: DSLContext, limit: Long): SelectLimitPercentStep<Record8<String, String, String, String, String, String, String, String>> {
        return dslContext
            .select(d.NAME, d.KORP, d.SOCR, d.CODE, d.INDEX, d.GNINMB, d.UNO, d.OCATD)
            .from(d)
            .join(s).on(s.OCATD.eq(d.OCATD))
            .join(f).on(f.GNINMB.eq(s.GNINMB))
            .limit(limit)
    }
}
