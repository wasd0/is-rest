package com.wasd.isrest.service

import com.wasd.isrest.domain.Country

interface CountryService {

    fun findByCodeAndIso(code: Int, iso: String): Country?
}