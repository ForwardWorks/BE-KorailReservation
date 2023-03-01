package com.forwardworks.korail.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.forwardworks.korail.common.response.BaseResponseType

data class BaseResponse(
    @JsonProperty("code")
    val code: String,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("data")
    val data: Any? = null,
) {
    companion object {
        fun createBy(baseResponseType: BaseResponseType): BaseResponse {
            return BaseResponse(
                code = baseResponseType.code,
                message = baseResponseType.msg,
                data = null
            )
        }
    }
}