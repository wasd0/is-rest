package com.wasd.isrest.service

import com.wasd.isrest.domain.Reference

interface ReferenceService {

    fun getReference(refHeaderId: Int, code: Int): Reference

    fun getReferences(refHeaderId: Int, codes: List<Int>): List<Reference>
}