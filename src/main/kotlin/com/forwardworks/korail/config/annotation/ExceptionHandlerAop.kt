package com.forwardworks.korail.config.annotation;

import org.springframework.stereotype.Component

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class ExceptionHandlerAop(
    val value: String = "",
)
