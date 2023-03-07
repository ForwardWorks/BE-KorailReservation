package com.forwardworks.korail.handler.exception.errorcode

import com.forwardworks.korail.utils.MessageConverter
import org.springframework.http.HttpStatus

enum class ServerErrorCode(
    val status: HttpStatus,
    val code: String,
) : ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    ;

    override fun getMessage() = MessageConverter.getMessage("error.properties.$code")
}