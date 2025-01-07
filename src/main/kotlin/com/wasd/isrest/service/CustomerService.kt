package com.wasd.isrest.service

import com.wasd.isrest.domain.Customer
import com.wasd.isrest.model.request.CustomerGetRequest
import com.wasd.isrest.model.response.CustomerSingleResponse

interface CustomerService : BaseEntityService<Customer, Long> {

    fun createOrGet(request: CustomerGetRequest): CustomerSingleResponse
}