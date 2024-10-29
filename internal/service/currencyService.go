package service

import (
	"fmt"
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	"wasd0/is-rest/internal/repository"
)

type CurrencyService interface {
	GetByCode(code string) (*model.Currencies, *dto.ServErr)
	GetById(id int32) (*model.Currencies, *dto.ServErr)
}

type currencyServiceImpl struct {
	currencyRepository repository.CurrencyRepository
}

func (c *currencyServiceImpl) GetById(id int32) (*model.Currencies, *dto.ServErr) {
	if currency, err := c.currencyRepository.GetById(id); err != nil {
		return nil, dto.NotFound(fmt.Sprintf("Currency not found by id: {%d}", id))
	} else {
		return currency, nil
	}
}

func (c *currencyServiceImpl) GetByCode(code string) (*model.Currencies, *dto.ServErr) {
	if currency, err := c.currencyRepository.GetByCode(code); err != nil {
		return nil, dto.NotFound("Currency not found by code: " + code)
	} else {
		return currency, nil
	}
}

func InitCurrencyService(currencyRepository repository.CurrencyRepository) CurrencyService {
	return &currencyServiceImpl{currencyRepository: currencyRepository}
}
