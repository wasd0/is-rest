package com.wasd.isrest.service

import com.wasd.isrest.model.request.PaymentRequestInitiateRequest
import com.wasd.isrest.model.response.PaymentRequestInitiateResponse

interface PaymentRequestService {
    
    fun initiate(request: PaymentRequestInitiateRequest): PaymentRequestInitiateResponse
}