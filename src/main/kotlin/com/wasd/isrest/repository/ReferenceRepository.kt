package com.wasd.isrest.repository

import com.wasd.isrest.domain.RefHeader
import com.wasd.isrest.domain.Reference
import org.springframework.data.jpa.repository.JpaRepository

interface ReferenceRepository : JpaRepository<Reference, Long> {

    fun getByHeaderAndCode(header: RefHeader, code: Int): Reference?

    fun findAllByHeaderAndCodeIn(header: RefHeader, codes: List<Int>): List<Reference>
}