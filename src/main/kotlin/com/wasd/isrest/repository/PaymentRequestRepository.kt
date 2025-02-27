package com.wasd.isrest.repository

import com.wasd.isrest.domain.PaymentRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.ZonedDateTime
import java.util.*

interface PaymentRequestRepository : JpaRepository<PaymentRequest, UUID> {

    @Query(
        "from payment_requests pr " +
                "where (pr.status.code = 1 and pr.createdAt <= :date1) " +
                "or (pr.status.code = 2 and pr.createdAt <= :date2)"
    )
    fun findAllExpired(
        @Param("date1")
        thresholdDate: ZonedDateTime,
        @Param("date2")
        thresholdDate2: ZonedDateTime
    ): List<PaymentRequest>
}