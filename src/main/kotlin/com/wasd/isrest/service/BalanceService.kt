package com.wasd.isrest.service

import com.wasd.isrest.domain.Balance
import com.wasd.isrest.model.request.BalanceCreateRequest
import com.wasd.isrest.model.request.BalanceGetRequest
import com.wasd.isrest.model.response.BalanceCreateResponse
import com.wasd.isrest.model.response.BalanceGetResponse

interface BalanceService {

    fun create(request: BalanceCreateRequest): BalanceCreateResponse

    fun getDataByCustomerAndCurrency(request: BalanceGetRequest): BalanceGetResponse

    fun getByCustomerIdAndCurrencyCode(customerId: Long, currencyCode: String): Balance

    fun getById(id: Long): BalanceGetResponse
}