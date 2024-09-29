package com.wasd.isrest.exception

import kotlin.reflect.KClass

class AlreadyExistsException(target: KClass<*>) : RuntimeException("${target.simpleName} already exists")