package com.wasd.isrest.controller

import com.wasd.isrest.model.RestResponse
import com.wasd.isrest.model.request.PaymentRequestInitiateRequest
import com.wasd.isrest.model.response.PaymentRequestInitiateResponse
import com.wasd.isrest.service.PaymentRequestService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/payment-request")
class PaymentRequestController(val paymentRequestService: PaymentRequestService) {
    @PostMapping
    fun initiate(@Valid @RequestBody request: PaymentRequestInitiateRequest): RestResponse<PaymentRequestInitiateResponse> {
        return RestResponse(paymentRequestService.initiate(request))
    }
}