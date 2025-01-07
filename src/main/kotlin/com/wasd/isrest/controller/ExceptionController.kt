package com.wasd.isrest.controller

import com.wasd.isrest.exception.AlreadyExistsException
import com.wasd.isrest.exception.AuthException
import com.wasd.isrest.exception.NotFoundException
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExistsException(ex: AlreadyExistsException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.CONFLICT, ex.message)
    }

    @ExceptionHandler(AuthException::class)
    fun handleUserAuthenticationException(ex: AuthException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.UNAUTHORIZED, ex.message)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.NOT_FOUND, ex.message)
    }
    
    @ExceptionHandler(BadRequestException::class)
    fun handleNotFoundException(ex: BadRequestException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.BAD_REQUEST, ex.message)
    }

    private fun getExceptionResponse(status: HttpStatus, message: String?): ResponseEntity<ProblemDetail> {
        val detail = ProblemDetail.forStatusAndDetail(status, message)
        return ResponseEntity.of(detail).build()
    }
}