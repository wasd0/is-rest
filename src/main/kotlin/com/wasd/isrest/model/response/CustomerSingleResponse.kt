package com.wasd.isrest.model.response

import java.time.ZonedDateTime

data class CustomerSingleResponse(
    val id: Long,
    val telegramId: Long?,
    val blocked: Boolean,
    val createDate: ZonedDateTime,
    val countryIso: String
)