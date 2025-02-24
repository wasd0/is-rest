package com.wasd.isrest.controller

import com.wasd.isrest.exception.AlreadyExistsException
import com.wasd.isrest.exception.AuthException
import com.wasd.isrest.exception.NotFoundException
import jakarta.validation.ConstraintViolationException
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
        return getExceptionResponse(HttpStatus.CONFLICT, ex)
    }

    @ExceptionHandler(AuthException::class)
    fun handleUserAuthenticationException(ex: AuthException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.UNAUTHORIZED, ex)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.NOT_FOUND, ex)
    }
    
    @ExceptionHandler(BadRequestException::class)
    fun handleNotFoundException(ex: BadRequestException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.BAD_REQUEST, ex)
    }
    
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ProblemDetail> {
        return getExceptionResponse(HttpStatus.BAD_REQUEST, ex.constraintViolations.last().message)
    }

    private fun getExceptionResponse(status: HttpStatus, ex: Exception): ResponseEntity<ProblemDetail> {
        val detail: ProblemDetail = ProblemDetail.forStatusAndDetail(status, ex.message ?: "Internal error")
        return ResponseEntity.of(detail).build()
    }

    private fun getExceptionResponse(status: HttpStatus, message: String): ResponseEntity<ProblemDetail> {
        val detail: ProblemDetail = ProblemDetail.forStatusAndDetail(status, message)
        return ResponseEntity.of(detail).build()
    }
}