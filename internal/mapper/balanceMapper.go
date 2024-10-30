package mapper

import (
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	"wasd0/is-rest/internal/utils"
)

func BalanceToCreateResponse(balance model.Balances, currency model.Currencies) *dto.BalanceCreateResponse {
	return &dto.BalanceCreateResponse{
		BalanceId: balance.ID,
		Currency:  currency.Code,
	}
}

func BalanceToGetResponse(balance model.Balances, currency model.Currencies) *dto.BalanceGetResponse {
	sum := utils.GetAmountFloat(balance.Sum, currency.Dimension).String()
	return &dto.BalanceGetResponse{
		BalanceId: balance.ID,
		Currency:  currency.Code,
		Sum:       &sum,
	}
}
