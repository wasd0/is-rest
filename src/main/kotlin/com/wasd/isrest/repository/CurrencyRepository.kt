package com.wasd.isrest.repository

import com.wasd.isrest.domain.Currency
import org.springframework.data.jpa.repository.JpaRepository

interface CurrencyRepository : JpaRepository<Currency, Int> {
    fun findByCode(code: String): Currency?
}