package com.forwardworks.korail.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors.basePackage
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun docket() = Docket(DocumentationType.SWAGGER_2)
        .enable(true)
        .apiInfo(ApiInfoBuilder()
            .description("Ticketing for Korail Train")
            .title("코레일 기차 예메 시스템 API")
            .version("V1.0")
            .contact(
                Contact("ForwardWorks"
                    , "https://forwardworks.atlassian.net/wiki/spaces/SD/overview?homepageId=229378"
                    , "taehyeonkim@kakao.com")
            )
            .build())
        .useDefaultResponseMessages(false)
        .ignoredParameterTypes(
            WebSession::class.java,
            ServerHttpRequest::class.java,
            ServerHttpResponse::class.java,
            ServerWebExchange::class.java)
        .genericModelSubstitutes(
            Optional::class.java,
            Mono::class.java,
            Flux::class.java,
            ResponseEntity::class.java)
        .select()
        .apis(basePackage("com.forwardworks.korail"))
        .build()

}