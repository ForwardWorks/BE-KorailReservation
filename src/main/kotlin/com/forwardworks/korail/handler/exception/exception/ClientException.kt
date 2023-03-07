package com.forwardworks.korail.handler.exception.exception

import com.forwardworks.korail.handler.exception.errorcode.ClientErrorCode

class ClientException(
    val errorCode: ClientErrorCode,
    override val message: String = errorCode.getMessage(),
) : RuntimeException(message)