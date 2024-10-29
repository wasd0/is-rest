package dto

import (
	"errors"
	"net/http"
)

type BalanceCreateRequest struct {
	CustomerId int64 `json:"customerId"`
}

func (b *BalanceCreateRequest) Bind(_ *http.Request) error {
	if b.CustomerId <= 0 {
		return errors.New("invalid customerId")
	}

	return nil
}

type BalanceCreateResponse struct {
	BalanceId int64  `json:"balanceId"`
	Currency  string `json:"currency"`
}

type BalanceGetRequest struct {
	CustomerId int64   `json:"customerId"`
	Currency   *string `json:"currency"`
}

func (b *BalanceGetRequest) Bind(_ *http.Request) error {
	if b.CustomerId <= 0 {
		return errors.New("invalid customerId")
	}

	if (*b).Currency != nil && *b.Currency == "" {
		return errors.New("invalid currency")
	}

	return nil
}

type BalanceGetResponse struct {
	BalanceId int64   `json:"balanceId"`
	Currency  string  `json:"currency"`
	Sum       float64 `json:"sum"`
}
