package utils

import (
	"math"
	"math/big"
)

func GetAmountFloat(amount int64, dimension int32) *big.Float {
	bigAmount := new(big.Float).SetInt64(amount)
	divisor := new(big.Float).SetFloat64(math.Pow(10, float64(dimension)))
	result := new(big.Float).Quo(bigAmount, divisor)
	resultStr := result.Text('f', int(dimension))
	result.SetString(resultStr)
	return result
}
