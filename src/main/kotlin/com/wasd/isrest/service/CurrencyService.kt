package com.wasd.isrest.service

import com.wasd.isrest.domain.Currency

interface CurrencyService {
    fun findByCode(code: String): Currency?
    fun getByCode(code: String): Currency
}