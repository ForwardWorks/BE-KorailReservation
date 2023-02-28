package com.taehyeon.korail.handler.exception

import com.taehyeon.korail.common.BaseResponse
import com.taehyeon.korail.common.response.BaseResponseType
import com.taehyeon.korail.config.annotation.ExceptionHandlerAop
import com.taehyeon.korail.config.logger
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import java.util.stream.Collectors

@ControllerAdvice
class GlobalErrorControllerAdvice : ResponseEntityExceptionHandler() {
    val log = logger<GlobalErrorControllerAdvice>()

    @ExceptionHandlerAop
    override fun handleMethodArgumentNotValid(
        e: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error("MethodArgumentNotValidException :: {}", e.message)

        exceptionStackTraceLogging(e)

        val errorList: MutableList<String?>? = e.bindingResult.fieldErrors
                                                .stream()
                                                .map { value -> value.defaultMessage }
                                                .collect(Collectors.toList())

        val body: BaseResponse = BaseResponse(
            code = BaseResponseType.PARAM_ERROR.code
            , message= java.lang.String.format(BaseResponseType.PARAM_ERROR.msg, errorList.toString())
        )

        return ResponseEntity<Any>(
            body, HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandlerAop
    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val body: BaseResponse = BaseResponse(code = BaseResponseType.PARAM_ERROR.code
                                              , message = ex.bindingResult.fieldError!!.defaultMessage.toString())

        return ResponseEntity<Any>(
            body,
            status
        )
    }

    @ExceptionHandlerAop
    override fun handleServletRequestBindingException(
        e: ServletRequestBindingException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error("BindException :: {}", e.message)
        exceptionStackTraceLogging(e)
        return internalServerError(e)
    }

    @ExceptionHandlerAop
    override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        log.error("HttpMessageNotReadableException :: {}", e.message)

        exceptionStackTraceLogging(e)

        val body: BaseResponse = BaseResponse(code = BaseResponseType.PARAM_ERROR.code, message = "요청 JSON 형태 오류")

        return ResponseEntity<Any>(
            body, HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandlerAop
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<BaseResponse> {
        val body: BaseResponse = BaseResponse(code = exception.code, message = exception.message)

        return ResponseEntity<BaseResponse>(
            body,
            exception.httpStatus
        )
    }

    @ExceptionHandlerAop
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(exception: RuntimeException): ResponseEntity<Any> {
        exception.printStackTrace()
        val body: BaseResponse = BaseResponse(code = BaseResponseType.SEND_ERROR.code, message = "Server Error")

        return ResponseEntity<Any>(
            body, BaseResponseType.SEND_ERROR.httpStatus
        )
    }

    @ExceptionHandlerAop
    protected override fun handleExceptionInternal(
        exception: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error("오류 발생!", exception)

        val failMessage: BaseResponse = BaseResponse(code = BaseResponseType.SEND_ERROR.code, message = "Server Error")

        return ResponseEntity<Any>(
            failMessage,
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    protected fun exceptionStackTraceLogging(e: Exception) {
        val stringBuffer = StringBuffer()

        Arrays.stream(e.stackTrace).forEach {
                stackTraceElement: StackTraceElement? -> stringBuffer.append(stackTraceElement).append("\n")
        }

        log.error(stringBuffer.toString())
    }

    protected fun internalServerError(e: Exception): ResponseEntity<Any> {
        val body: BaseResponse = BaseResponse(code = BaseResponseType.SEND_ERROR.code, message = e.message.toString())

        return ResponseEntity<Any>(
            body, HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}