package com.forwardworks.korail.handler.exception

import com.forwardworks.korail.handler.exception.errorcode.CustomErrorCode
import com.forwardworks.korail.handler.exception.exception.CustomException
import org.slf4j.LoggerFactory
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import java.io.PrintWriter
import java.io.StringWriter
import java.time.LocalDateTime

@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): Map<String, Any> {
        val error = this.getError(request)

        val errorAttributeResponse = when (error) {
            is CustomException -> handleException(error)
            else -> handleUnknownException()
        }

        val errorAttributes = mutableMapOf(
            "timestamp" to LocalDateTime.now().toString(),
            "status" to errorAttributeResponse.status.value(),
            "message" to errorAttributeResponse.message,
            "path" to request.uri().path
        )

        if (errorAttributeResponse.status.is5xxServerError) {
            log.error(getStackTrace(error))
        }

        return errorAttributes
    }

    fun handleException(ex: CustomException) = ErrorAttributeResponse(
        status = ex.errorCode.status,
        message = ex.errorCode.getMessage()
    )

    fun handleUnknownException() = ErrorAttributeResponse(
        status = CustomErrorCode.INTERNAL_SERVER_ERROR.status,
        message = CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage()
    )

    private fun getStackTrace(error: Throwable?): String? {
        return error?.let {
            val stackTrace = StringWriter()
            error.printStackTrace(PrintWriter(stackTrace))
            stackTrace.toString()
        }
    }

    class ErrorAttributeResponse(
        val status: HttpStatus,
        val message: String = ""
    )
}