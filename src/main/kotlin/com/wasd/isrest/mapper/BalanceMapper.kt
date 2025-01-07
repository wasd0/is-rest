package com.wasd.isrest.mapper

import com.wasd.isrest.domain.Balance
import com.wasd.isrest.model.response.BalanceCreateResponse
import com.wasd.isrest.model.response.BalanceGetResponse
import com.wasd.isrest.utils.CurrencyUtils
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class BalanceMapper {

    @Mapping(target = "balanceId", source = "id")
    @Mapping(target = "currency", source = "currency.code")
    @Mapping(target = "sum", expression = "java(convertAmount(balance))")
    abstract fun toGetResponse(balance: Balance): BalanceGetResponse

    @Mapping(target = "balanceId", source = "id")
    @Mapping(target = "currency", source = "currency.code")
    abstract fun toCreateResponse(balance: Balance): BalanceCreateResponse
    
    protected fun convertAmount(balance: Balance) : Double? {
        if (balance.sum == 0L || balance.currency == null) {
            return null
        }
        
        return CurrencyUtils.convertAmount(balance.sum, balance.currency!!.dimension).toDouble()
    }
}