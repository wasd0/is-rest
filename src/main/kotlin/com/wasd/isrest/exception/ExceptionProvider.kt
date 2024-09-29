package com.wasd.isrest.exception

import org.springframework.stereotype.Component

@Component
class ExceptionProvider {
    fun <ID : Any> notFoundException(id: ID): NotFoundException {
        return NotFoundException(id)
    }
}