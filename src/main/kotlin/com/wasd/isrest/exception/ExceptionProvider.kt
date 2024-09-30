package com.wasd.isrest.exception

import com.wasd.isrest.utils.DateTimeHolder
import com.wasd.isrest.utils.Logger
import org.springframework.stereotype.Component

@Component
class ExceptionProvider(
    private val dateTimeHolder: DateTimeHolder,
) {
    fun <ID : Any> notFoundException(id: ID): NotFoundException {
        Logger.Log.warn("[${dateTimeHolder.now()}] Not found exception for id: $id")
        return NotFoundException(id)
    }
}