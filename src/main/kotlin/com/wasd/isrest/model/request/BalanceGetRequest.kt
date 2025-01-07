package com.wasd.isrest.model.request

data class BalanceGetRequest(
    val customerId: Long?,
    val telegramId: Long?,
    val currencyCode: String?,
)
