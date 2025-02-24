package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.PaymentRequest
import com.wasd.isrest.keys.RefHeaderKeys
import com.wasd.isrest.keys.RefKeys
import com.wasd.isrest.model.request.PaymentRequestInitiateRequest
import com.wasd.isrest.model.response.PaymentRequestInitiateResponse
import com.wasd.isrest.repository.PaymentRequestRepository
import com.wasd.isrest.service.BalanceService
import com.wasd.isrest.service.CurrencyService
import com.wasd.isrest.service.PaymentRequestService
import com.wasd.isrest.utils.CurrencyUtils
import com.wasd.isrest.utils.DateTimeHolder
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentRequestServiceImpl(
    private val balanceService: BalanceService,
    private val dateTimeHolder: DateTimeHolder,
    private val currencyService: CurrencyService,
    private val referenceService: ReferenceService,
    private val paymentRequestRepository: PaymentRequestRepository,
) : PaymentRequestService {

    override fun initiate(request: PaymentRequestInitiateRequest): PaymentRequestInitiateResponse {

        if (request.customerId <= 0 || request.currency.isBlank()) {
            throw IllegalArgumentException("Illegal request data: customer id ${request.customerId}, currency: ${request.currency}")
        }

        //todo: добавить скедулер, который каждые 30 минут проверяет невыполненные запросы на пополнение и отменяет их
        //todo: добавить логику отправки запроса на пополнение на сторонний сервис (интеграцию)

        val payment = createPaymentRequest(request.currency, request.customerId, request.amount)
        paymentRequestRepository.save(payment)

        return PaymentRequestInitiateResponse(
            payment.id ?: throw IllegalArgumentException("Payment is not created")
        )
    }

    private fun createPaymentRequest(
        currencyCode: String,
        customerId: Long,
        sum: BigDecimal
    ): PaymentRequest {
        val paymentRequest = PaymentRequest()
        val currency = currencyService.getByCode(currencyCode)
        paymentRequest.createdAt = dateTimeHolder.now()
        paymentRequest.currency = currency
        paymentRequest.balance = balanceService.getByCustomerIdAndCurrencyCode(customerId, currencyCode)
        paymentRequest.sum = CurrencyUtils.convertAmount(sum, currency.dimension)
        paymentRequest.status = referenceService.getReference(RefHeaderKeys.PAYMENT_REQUEST_STATUS, RefKeys.PAYMENT_REQUEST_STATUS_CREATED)
        return paymentRequest
    }
}