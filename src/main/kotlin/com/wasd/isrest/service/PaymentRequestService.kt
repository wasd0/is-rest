package com.wasd.isrest.service

import com.wasd.isrest.domain.PaymentRequest
import com.wasd.isrest.model.PaymentRequestListItem
import com.wasd.isrest.model.PaymentRequestSingleData
import com.wasd.isrest.model.request.PaymentRequestInitiateRequest
import com.wasd.isrest.model.response.PaymentRequestInitiateResponse
import java.time.ZonedDateTime
import java.util.*

interface PaymentRequestService : BaseEntityService<PaymentRequest, UUID> {

    /**
     * Creating a request to top up the balance
     * @param request data for topping up the balance: amount, user, currency
     * @return data of the created request only
     * */
    fun initiate(request: PaymentRequestInitiateRequest): PaymentRequestInitiateResponse

    /**
     * Receive the full date of the recharge request
     * @param id payment request UUID
     * @return full info about payment request
     * */
    fun getSingleDataById(id: UUID): PaymentRequestSingleData

    fun findAllExpired(thresholdDate: ZonedDateTime, thresholdDate2: ZonedDateTime): List<PaymentRequest>

    fun findAll(): List<PaymentRequestListItem>
}