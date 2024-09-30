package com.wasd.isrest.model.customer

import java.time.ZonedDateTime

data class CustomerResponse(
    val id: Long,
    val telegramId: Long?,
    val blocked: Boolean,
    val createDate: ZonedDateTime,
    val countryIso: String
)