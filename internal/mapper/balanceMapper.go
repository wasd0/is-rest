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
	return &dto.BalanceGetResponse{
		BalanceId: balance.ID,
		Currency:  currency.Code,
		Sum:       utils.GetAmountFloat(balance.Sum, currency.Dimension),
	}
}
