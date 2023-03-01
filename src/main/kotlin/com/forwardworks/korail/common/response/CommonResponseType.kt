package com.forwardworks.korail.common.response;


import org.springframework.http.HttpStatus;

interface CommonResponseType {
    fun getMsg(): String
    fun getCode(): String
    fun getHttpStatus(): HttpStatus;
}
