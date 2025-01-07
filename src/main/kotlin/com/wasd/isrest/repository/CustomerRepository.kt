package com.wasd.isrest.repository

import com.wasd.isrest.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByTelegramId(telegramId: Long): Customer?
}