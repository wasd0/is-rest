package com.wasd.isrest.mapper

import com.wasd.isrest.domain.Customer
import com.wasd.isrest.model.response.CustomerSingleResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class CustomerMapper {

    @Mapping(target = "countryIso", source = "country.iso")
    abstract fun customerToSingleResponse(customer: Customer): CustomerSingleResponse
}