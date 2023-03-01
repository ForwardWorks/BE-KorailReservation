package com.forwardworks.korail.handler.exception

import com.forwardworks.korail.common.response.CommonResponseType
import org.springframework.http.HttpStatus

class BaseException(
    val commonResponseType: CommonResponseType,
    var reason: String = "",
) : RuntimeException() {
    override val message: String
        get() = java.lang.String.format(commonResponseType.getMsg(), reason)
    val code: String
        get() = commonResponseType.getCode()
    val httpStatus: HttpStatus
        get() = commonResponseType.getHttpStatus()
}