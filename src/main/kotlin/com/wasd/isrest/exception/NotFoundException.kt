package com.wasd.isrest.exception

class NotFoundException(data: Any) :
    RuntimeException("Entity not found by data: $data")