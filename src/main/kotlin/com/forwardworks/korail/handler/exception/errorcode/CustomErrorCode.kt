package com.forwardworks.korail.handler.exception.errorcode

import com.forwardworks.korail.utils.MessageConverter
import org.springframework.http.HttpStatus

enum class CustomErrorCode(
    val status: HttpStatus,
    val code: String,
) : ErrorCode {
    REQUEST_ERROR(HttpStatus.BAD_REQUEST, "E0001"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    ;

    override fun getMessage() = MessageConverter.getMessage("error.properties.$code")
}