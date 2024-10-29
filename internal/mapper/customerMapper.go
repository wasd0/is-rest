package mapper

import (
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
)

func CustomerToGetResponse(customer model.Customers, country *model.Countries) dto.CustomerGetResponse {
	return dto.CustomerGetResponse{
		ID:         customer.ID,
		TelegramID: customer.TelegramID,
		Blocked:    customer.Blocked,
		CreateDate: customer.CreateDate,
		CountryIso: country.Iso,
	}
}
