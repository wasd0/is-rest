package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Currency
import com.wasd.isrest.exception.NotFoundException
import com.wasd.isrest.repository.CurrencyRepository
import com.wasd.isrest.service.CurrencyService
import org.springframework.stereotype.Service

@Service
class CurrencyServiceImpl(
    private val currencyRepository: CurrencyRepository
) : CurrencyService {
    override fun findByCode(code: String): Currency? {
        return currencyRepository.findByCode(code)
    }
    
    override fun getByCode(code: String): Currency {
        return currencyRepository.findByCode(code) ?: throw NotFoundException("Code: $code")
    }
}