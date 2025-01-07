package com.wasd.isrest.model.response

import java.time.ZonedDateTime

data class ExceptionResponse(
    val message: String,
    val code: Int,
    val time: ZonedDateTime,
)