package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Customer
import com.wasd.isrest.exception.ExceptionProvider
import com.wasd.isrest.keys.CountryKeys
import com.wasd.isrest.mapper.CustomerMapper
import com.wasd.isrest.model.customer.CustomerGetRequest
import com.wasd.isrest.model.customer.CustomerResponse
import com.wasd.isrest.repository.CustomerRepository
import com.wasd.isrest.service.AbstractBaseEntityService
import com.wasd.isrest.service.CountryService
import com.wasd.isrest.service.CustomerService
import com.wasd.isrest.utils.DateTimeHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    exceptionProvider: ExceptionProvider,
    private val customerMapper: CustomerMapper,
    private val countryService: CountryService,
    private val dateTimeHolder: DateTimeHolder
) : AbstractBaseEntityService<Customer, Long>(customerRepository, exceptionProvider), CustomerService {

    @Transactional
    override fun createOrGet(request: CustomerGetRequest): CustomerResponse {

        var customer: Customer? = null
        
        if (request.id != null) {
            customer = findEntity(request.id)
        } else if (request.telegramId != null) {
            customer = customerRepository.findByTelegramId(request.telegramId)
        }
        
        if (customer != null) {
            return customerMapper.customerToSingleResponse(customer)
        }

        customer = Customer()
        customer.country = countryService.findByCodeAndIso(CountryKeys.RU_CODE, CountryKeys.RU_ISO)
        customer.createDate = dateTimeHolder.now()
        customer.telegramId = request.telegramId
        return customerMapper.customerToSingleResponse(customerRepository.save(customer))
    }
}