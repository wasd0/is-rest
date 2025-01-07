package com.wasd.isrest.model.response

data class BalanceGetResponse(
    var balanceId: Long = 0,
    var currency: String = "",
    var sum: Double? = null
)
