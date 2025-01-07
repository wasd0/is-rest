package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Country
import com.wasd.isrest.repository.CountryRepository
import com.wasd.isrest.service.CountryService
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository
) : CountryService {
    override fun findByCodeAndIso(code: Int, iso: String): Country? {
        return countryRepository.findByCodeAndIso(code, iso)
    }
}