package com.wasd.isrest.controller

import com.wasd.isrest.exception.AlreadyExistsException
import com.wasd.isrest.exception.AuthException
import com.wasd.isrest.exception.NotFoundException
import com.wasd.isrest.model.response.ExceptionResponse
import com.wasd.isrest.utils.DateTimeHolder
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionController(private val dateTimeHolder: DateTimeHolder) {

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExistsException(ex: AlreadyExistsException): ExceptionResponse {
        return getExceptionResponse(HttpStatus.CONFLICT, ex)
    }

    @ExceptionHandler(AuthException::class)
    fun handleUserAuthenticationException(ex: AuthException): ExceptionResponse {
        return getExceptionResponse(HttpStatus.UNAUTHORIZED, ex)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ExceptionResponse {
        return getExceptionResponse(HttpStatus.NOT_FOUND, ex)
    }
    
    @ExceptionHandler(BadRequestException::class)
    fun handleNotFoundException(ex: BadRequestException): ExceptionResponse {
        return getExceptionResponse(HttpStatus.BAD_REQUEST, ex)
    }

    private fun getExceptionResponse(status: HttpStatus, ex: Exception): ExceptionResponse {
        return ExceptionResponse(ex.message?:"INTERNAL ERROR", status.value(), dateTimeHolder.now())
    }
}