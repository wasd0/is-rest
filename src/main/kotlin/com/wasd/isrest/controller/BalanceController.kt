package com.wasd.isrest.controller

import com.wasd.isrest.model.RestResponse
import com.wasd.isrest.model.request.BalanceCreateRequest
import com.wasd.isrest.model.request.BalanceGetRequest
import com.wasd.isrest.model.response.BalanceCreateResponse
import com.wasd.isrest.model.response.BalanceGetResponse
import com.wasd.isrest.service.BalanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/balance")
class BalanceController(private val balanceService: BalanceService) {
    
    @PostMapping
    fun create(@RequestBody request: BalanceCreateRequest): RestResponse<BalanceCreateResponse> {
        return RestResponse(balanceService.create(request))
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") id: Long): RestResponse<BalanceGetResponse> {
        return RestResponse(balanceService.getById(id))
    }

    @GetMapping
    fun getByCustomer(@ModelAttribute request: BalanceGetRequest): RestResponse<BalanceGetResponse> {
        return RestResponse(balanceService.getDataByCustomerAndCurrency(request))
    }
}