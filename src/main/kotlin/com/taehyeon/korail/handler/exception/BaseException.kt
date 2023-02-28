package com.taehyeon.korail.handler.exception

import com.taehyeon.korail.common.response.CommonResponseType
import org.springframework.http.HttpStatus

class BaseException : RuntimeException {
    private val commonResponseType: CommonResponseType
    private var reason: String

    constructor(commonResponseType: CommonResponseType) {
        this.commonResponseType = commonResponseType
        reason = ""
    }

    constructor(commonResponseType: CommonResponseType, reason: String) {
        this.commonResponseType = commonResponseType
        this.reason = reason
    }

    override val message: String
        get() = java.lang.String.format(commonResponseType.getMsg(), reason)
    val code: String
        get() = commonResponseType.getCode()
    val httpStatus: HttpStatus
        get() = commonResponseType.getHttpStatus()
}