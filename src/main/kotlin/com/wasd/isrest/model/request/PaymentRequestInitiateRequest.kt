package com.wasd.isrest.model.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class PaymentRequestInitiateRequest(
    @DecimalMin("1.0")
    val amount: BigDecimal,
    @NotNull
    val currency: String,
    @Positive
    val customerId: Long
)
