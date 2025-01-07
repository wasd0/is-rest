package com.wasd.isrest.repository

import com.wasd.isrest.domain.Balance
import com.wasd.isrest.domain.Currency
import org.springframework.data.jpa.repository.JpaRepository

interface BalanceRepository : JpaRepository<Balance, Long> {
    
    fun findByCustomer_IdAndCurrency(customerId: Long, currency: Currency): Balance?
    
    fun findByCustomer_TelegramIdAndCurrency(telegramId: Long, currency: Currency): Balance?
}