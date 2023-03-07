package com.forwardworks.korail.handler.exception.exception

import com.forwardworks.korail.handler.exception.errorcode.ServerErrorCode

class ServerException(
    val errorCode: ServerErrorCode,
    override val message: String = errorCode.getMessage(),
) : RuntimeException(message)