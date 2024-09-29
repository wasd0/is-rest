package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Customer
import com.wasd.isrest.exception.ExceptionProvider
import com.wasd.isrest.keys.CountryKeys
import com.wasd.isrest.mapper.CustomerMapper
import com.wasd.isrest.model.customer.CustomerGetRequest
import com.wasd.isrest.model.customer.CustomerResponse
import com.wasd.isrest.repository.CustomerRepository
import com.wasd.isrest.service.CountryService
import com.wasd.isrest.service.CustomerService
import com.wasd.isrest.utils.DateTimeHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val exceptionProvider: ExceptionProvider,
    private val customerMapper: CustomerMapper,
    private val countryService: CountryService,
    private val dateTimeHolder: DateTimeHolder
) : CustomerService {

    @Transactional
    override fun createOrGet(request: CustomerGetRequest): CustomerResponse {

        if (request.id != null) {
            val customer = customerRepository.findById(request.id)
                .orElseThrow({ exceptionProvider.notFoundException(request.id) })
            return customerMapper.customerToSingleResponse(customer)
        } else if (request.telegramId != null) {
            val customer = customerRepository.findByTelegramId(request.telegramId)
                .orElseThrow({ exceptionProvider.notFoundException(request.telegramId) })
            return customerMapper.customerToSingleResponse(customer)
        }

        val new = Customer()
        new.country = countryService.findByCodeAndIso(CountryKeys.RU_CODE, CountryKeys.RU_ISO)
        new.createDate = dateTimeHolder.now()
        return customerMapper.customerToSingleResponse(customerRepository.save(new))
    }
}