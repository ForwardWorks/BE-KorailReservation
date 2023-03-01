package com.forwardworks.korail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication(
    exclude = [DataSourceAutoConfiguration::class]
)
@EnableWebFlux
@ConfigurationPropertiesScan
class KorailReservationApplication

fun main(args: Array<String>) {
    runApplication<KorailReservationApplication>(*args)
}
