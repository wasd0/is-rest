package com.wasd.isrest.scheduler

import com.wasd.isrest.keys.RefHeaderKeys
import com.wasd.isrest.keys.RefKeys
import com.wasd.isrest.service.PaymentRequestService
import com.wasd.isrest.service.ReferenceService
import com.wasd.isrest.utils.DateTimeHolder
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class PaymentRequestScheduler(
    private val paymentRequestService: PaymentRequestService,
    private val dateTimeHolder: DateTimeHolder,
    private val referenceService: ReferenceService
) {
    @Scheduled(fixedRateString = "\${time.scheduler.timer.paymentRequestExpire}", timeUnit = TimeUnit.SECONDS)
    fun cancelExpiredPaymentRequests() {
        val now = dateTimeHolder.now()
        val createdRequestsExpireDate = now.minusMinutes(10)
        val inProgressRequestsExpireDate = now.minusMinutes(30)
        val expiredStatus = referenceService.getReference(
            RefHeaderKeys.PAYMENT_REQUEST_STATUS,
            RefKeys.PAYMENT_REQUEST_STATUS_EXPIRED
        )

        val expiredPaymentRequests = paymentRequestService.findAllExpired(
            createdRequestsExpireDate,
            inProgressRequestsExpireDate
        )

        for (paymentRequest in expiredPaymentRequests) {
            paymentRequest.status  = expiredStatus
        }
        paymentRequestService.saveAll(expiredPaymentRequests)
    }
}