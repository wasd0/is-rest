package com.wasd.isrest.service

import com.wasd.isrest.model.customer.CustomerGetRequest
import com.wasd.isrest.model.customer.CustomerResponse

interface CustomerService {

    fun createOrGet(request: CustomerGetRequest): CustomerResponse
}