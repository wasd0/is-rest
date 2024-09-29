package com.wasd.isrest.repository

import com.wasd.isrest.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByTelegramId(telegramId: Long): Optional<Customer>
}