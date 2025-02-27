package com.wasd.isrest.model

import java.util.*

data class PaymentRequestListItem(
    val id: UUID,
    val sum: String,
    val currencyCode: String,
    val status: String
)
