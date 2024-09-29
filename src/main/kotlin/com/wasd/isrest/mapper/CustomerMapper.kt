package com.wasd.isrest.mapper

import com.wasd.isrest.domain.Customer
import com.wasd.isrest.model.customer.CustomerResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CustomerMapper {

    @Mapping(target = "countryIso", source = "country.iso")
    fun customerToSingleResponse(customer: Customer): CustomerResponse
}