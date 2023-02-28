package com.taehyeon.korail.config.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExceptionHandlerAop {
    String value() default "";
}
