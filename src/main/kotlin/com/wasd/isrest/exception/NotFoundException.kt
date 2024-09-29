package com.wasd.isrest.exception

class NotFoundException(id: Any) :
    RuntimeException("Entity not found by id: $id")