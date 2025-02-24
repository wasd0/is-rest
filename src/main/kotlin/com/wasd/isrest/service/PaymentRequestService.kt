package com.wasd.isrest.service

import com.wasd.isrest.model.PaymentRequestSingleData
import com.wasd.isrest.model.request.PaymentRequestInitiateRequest
import com.wasd.isrest.model.response.PaymentRequestInitiateResponse
import java.util.*

interface PaymentRequestService {

    fun initiate(request: PaymentRequestInitiateRequest): PaymentRequestInitiateResponse
    
    fun getSingleDataById(id: UUID): PaymentRequestSingleData
}