package com.forwardworks.korail.application.service

import com.forwardworks.korail.handler.exception.errorcode.ClientErrorCode
import com.forwardworks.korail.handler.exception.exception.ClientException
import org.springframework.stereotype.Service

@Service
class ReserveService() {
    fun reserve() {
        throw ClientException(ClientErrorCode.REQUEST_ERROR)
    }
}