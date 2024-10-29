package dto

import (
	"errors"
	"math/big"
	"net/http"
)

type BalanceCreateRequest struct {
	CustomerId   int64   `json:"customerId"`
	CurrencyCode *string `json:"currencyCode"`
}

func (b *BalanceCreateRequest) Bind(_ *http.Request) error {
	if b.CustomerId <= 0 {
		return errors.New("invalid customerId")
	}

	if (*b).CurrencyCode != nil && *b.CurrencyCode == "" {
		return errors.New("invalid currencyCode")
	}

	return nil
}

type BalanceCreateResponse struct {
	BalanceId int64  `json:"balanceId"`
	Currency  string `json:"currency"`
}

type BalanceGetRequest struct {
	CustomerId   *int64  `json:"customerId"`
	TelegramId   *int64  `json:"telegramId"`
	CurrencyCode *string `json:"currencyCode"`
}

type BalanceGetResponse struct {
	BalanceId int64      `json:"balanceId"`
	Currency  string     `json:"currency"`
	Sum       *big.Float `json:"sum"`
}
