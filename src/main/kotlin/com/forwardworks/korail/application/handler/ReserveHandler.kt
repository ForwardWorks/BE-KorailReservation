package com.forwardworks.korail.application.handler

import com.forwardworks.korail.application.service.ReserveService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class ReserveHandler(
    private val reserveService: ReserveService,
) {

    suspend fun reserve(request: ServerRequest): ServerResponse {
        return withContext(Dispatchers.Default) {
            reserveService.reserve()
            ServerResponse.noContent().buildAndAwait()
        }
    }
}