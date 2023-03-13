package com.forwardworks.korail.application.router

import com.forwardworks.korail.application.handler.ReserveHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class ReserveRouter(
    private val reserveHandler: ReserveHandler,
) {
    @Bean
    fun routeReserve(): RouterFunction<ServerResponse> = coRouter {
        (accept(MediaType.APPLICATION_JSON) and "/korail").nest {
            POST("/reserve", reserveHandler::reserve)
        }
    }
}