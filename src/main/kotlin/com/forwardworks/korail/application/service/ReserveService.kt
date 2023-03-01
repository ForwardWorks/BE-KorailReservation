package com.forwardworks.korail.application.service

import com.forwardworks.korail.handler.exception.errorcode.CustomErrorCode
import com.forwardworks.korail.handler.exception.exception.CustomException
import org.springframework.stereotype.Service

@Service
class ReserveService {
    fun reserve() {
        throw CustomException(CustomErrorCode.REQUEST_ERROR)
    }
}