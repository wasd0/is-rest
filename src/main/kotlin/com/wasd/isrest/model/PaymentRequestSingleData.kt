package com.wasd.isrest.model

import java.time.ZonedDateTime
import java.util.*

data class PaymentRequestSingleData(
    val id: UUID,
    val externalId: String?,
    val sum: String,
    val currencyCode: String,
    val balanceId: Long,
    val statusCode: String,
    val createdAt: ZonedDateTime
)
