package com.forwardworks.korail.handler.exception.exception

import com.forwardworks.korail.handler.exception.errorcode.CustomErrorCode

class CustomException(
    val errorCode: CustomErrorCode,
    override val message: String = errorCode.getMessage()
) : RuntimeException(message)