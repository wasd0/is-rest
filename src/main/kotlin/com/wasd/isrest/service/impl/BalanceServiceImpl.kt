package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Balance
import com.wasd.isrest.exception.NotFoundException
import com.wasd.isrest.keys.CurrencyKeys
import com.wasd.isrest.mapper.BalanceMapper
import com.wasd.isrest.model.request.BalanceCreateRequest
import com.wasd.isrest.model.request.BalanceGetRequest
import com.wasd.isrest.model.response.BalanceCreateResponse
import com.wasd.isrest.model.response.BalanceGetResponse
import com.wasd.isrest.repository.BalanceRepository
import com.wasd.isrest.service.BalanceService
import com.wasd.isrest.service.CurrencyService
import com.wasd.isrest.service.CustomerService
import com.wasd.isrest.utils.Logger
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service

@Service
class BalanceServiceImpl(
    private val balanceRepository: BalanceRepository,
    private val balanceMapper: BalanceMapper,
    private val currencyService: CurrencyService,
    private val customerService: CustomerService
) : BalanceService {
    override fun create(request: BalanceCreateRequest): BalanceCreateResponse {
        val currencyCode = request.currencyCode ?: CurrencyKeys.CODE_RUB
        val currency = currencyService.findByCode(currencyCode)
            ?: throw BadRequestException("Invalid currency: $currencyCode")
        var balance = balanceRepository.findByCustomer_IdAndCurrency(request.customerId, currency)
        if (balance != null) {
            return balanceMapper.toCreateResponse(balance)
        }
        balance = Balance()
        balance.customer = customerService.getEntity(request.customerId)
        balance.currency = currency
        balanceRepository.save(balance)
        return balanceMapper.toCreateResponse(balance)
    }

    override fun getDataByCustomerAndCurrency(request: BalanceGetRequest): BalanceGetResponse {
        val currencyCode = request.currencyCode ?: CurrencyKeys.CODE_RUB
        var balance: Balance? = null
        if (request.customerId != null) {
            balance = getByCustomerIdAndCurrencyCode(request.customerId, currencyCode)
        } else if (request.telegramId != null) {
            val currency = currencyService.getByCode(currencyCode)
            balance = balanceRepository.findByCustomer_TelegramIdAndCurrency(request.telegramId, currency)
                ?: throw NotFoundException("Telegram id: ${request.telegramId}, Currency code: ${currency.code}")
        }
        if (balance != null) {
            return balanceMapper.toGetResponse(balance)
        }
        throw NotFoundException("${Logger.tryConvertJsonToString(request)}")
    }

    override fun getByCustomerIdAndCurrencyCode(customerId: Long, currencyCode: String): Balance {
        val currency = currencyService.getByCode(currencyCode)
        val balance = balanceRepository.findByCustomer_IdAndCurrency(customerId, currency)
            ?: throw NotFoundException("Customer: ${customerId}, Currency code: ${currency.code}")
        return balance;
    }

    override fun getById(id: Long): BalanceGetResponse {
        val balance =
            balanceRepository.findById(id).orElseThrow { NotFoundException("Id: $id") }
        return balanceMapper.toGetResponse(balance)
    }
}