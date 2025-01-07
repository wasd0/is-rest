package com.wasd.isrest.utils

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

object CurrencyUtils {
    
    fun convertAmount(amount: Long, dimension: Int): BigDecimal {
        return BigDecimal(amount)
            .divide(
                BigDecimal.valueOf(10.0.pow(dimension.toDouble())),
                dimension,
                RoundingMode.HALF_DOWN
            ).stripTrailingZeros()
    }
}