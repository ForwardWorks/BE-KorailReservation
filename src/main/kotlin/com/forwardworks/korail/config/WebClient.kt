package com.forwardworks.korail.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.*
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        val httpClient = HttpClient.create(ConnectionProvider.newConnection())
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10 * 1000)
            .responseTimeout(Duration.ofSeconds(60))
            .doOnConnected {
                it.addHandlerLast(ReadTimeoutHandler(60, TimeUnit.SECONDS))
            }

        return WebClient.builder()
            .codecs { it.defaultCodecs().maxInMemorySize(1024 * 1024 * 50) }
            .defaultHeader(USER_AGENT, USER_AGENT_VALUE)
            .defaultHeader(ACCEPT_ENCODING, ACCEPT_ENCODING_VALUE)
            .defaultHeader(ACCEPT, ACCEPT_VALUE)
            .defaultHeader(CONNECTION, CONNECTION_VALUE)
            .defaultHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    companion object {
        const val USER_AGENT_VALUE = "Dalvik/2.1.0 (Linux; U; Android 5.1.1; Nexus 4 Build/LMY48T)"
        const val ACCEPT_ENCODING_VALUE = "gzip"
        const val ACCEPT_VALUE = "*/*"
        const val CONNECTION_VALUE = "keep-alive"
        const val CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded"
    }
}