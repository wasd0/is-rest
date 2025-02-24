package com.wasd.isrest.service

import com.wasd.isrest.domain.Reference

interface ReferenceService {

    fun getReference(refHeaderId: Int, codeInteger: Int): Reference
}