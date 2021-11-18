package ru.jooq.test.jooqtest

import io.r2dbc.spi.ConnectionFactory
import javax.sql.DataSource
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy


@Configuration
class JooqConfiguration(
    private val connectionFactory: ConnectionFactory
) {

    @Bean(value = ["r2dbcDSLContext"])
    fun createContext(): DSLContext {
        return DSL.using(connectionFactory)
    }

    @Bean(value = ["jdbcDSLContext"])
    fun jdbcDSLContext(): DSLContext {
        return DefaultDSLContext(configuration())
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun lazyConnectionDataSource(): LazyConnectionDataSourceProxy {
        return LazyConnectionDataSourceProxy(dataSource())
    }

    @Bean
    fun connectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(lazyConnectionDataSource())
    }

    @Bean
    fun configuration(): DefaultConfiguration? {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider())
        return jooqConfiguration
    }
}