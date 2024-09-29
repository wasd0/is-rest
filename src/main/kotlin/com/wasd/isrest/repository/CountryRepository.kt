package com.wasd.isrest.repository

import com.wasd.isrest.domain.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, Int> {
    fun findByCodeAndIso(code: Int, iso: String): Country?
}