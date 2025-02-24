package com.wasd.isrest.mapper

import com.wasd.isrest.domain.PaymentRequest
import com.wasd.isrest.model.PaymentRequestSingleData
import com.wasd.isrest.utils.CurrencyUtils
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class PaymentRequestMapper {

    @Mapping(target = "sum", expression = "java(getSum(paymentRequest))")
    @Mapping(target = "currencyCode", source = "currency.code")
    @Mapping(target = "balanceId", source = "balance.id")
    @Mapping(target = "statusCode", source = "status.name")
    abstract fun paymentRequestToSingleData(paymentRequest: PaymentRequest): PaymentRequestSingleData

    fun getSum(paymentRequest: PaymentRequest): String {
        return CurrencyUtils.convertAmount(paymentRequest.sum!!, paymentRequest.currency!!.dimension)
            .toPlainString()
    }
}