package com.wasd.isrest.repository

import com.wasd.isrest.domain.PaymentRequest
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentRequestRepository : JpaRepository<PaymentRequest, UUID> {
}