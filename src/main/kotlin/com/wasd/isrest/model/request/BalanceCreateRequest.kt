package com.wasd.isrest.model.request

data class BalanceCreateRequest(
    val customerId: Long,
    val currencyCode: String?,
)