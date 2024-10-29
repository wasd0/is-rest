package dto

import (
	"errors"
	"net/http"
	"time"
)

type CustomerGetRequest struct {
	Id         *int64 `json:"id,omitempty"`
	TelegramId *int64 `json:"telegramId,omitempty"`
}

func (c CustomerGetRequest) Bind(_ *http.Request) error {
	if c.Id == nil && c.TelegramId == nil {
		return errors.New("missing required parameters")
	}

	return nil
}

type CustomerGetResponse struct {
	ID         int64     `json:"id"`
	TelegramID *int64    `json:"telegramId,omitempty"` // Pointer to allow nil value
	Blocked    bool      `json:"blocked"`
	CreateDate time.Time `json:"createDate"`
	CountryIso string    `json:"countryIso"`
}
