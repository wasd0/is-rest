package com.wasd.isrest.controller

import com.wasd.isrest.model.RestResponse
import com.wasd.isrest.model.customer.CustomerGetRequest
import com.wasd.isrest.model.customer.CustomerResponse
import com.wasd.isrest.service.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/customers")
class CustomerController(private val customerService: CustomerService) {
    
    @PostMapping
    fun getOrCreate(@RequestBody request: CustomerGetRequest): RestResponse<CustomerResponse> {
        return RestResponse(customerService.createOrGet(request))
    }
}