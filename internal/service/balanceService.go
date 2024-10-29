package service

import (
	"errors"
	"fmt"
	"github.com/go-jet/jet/v2/qrm"
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	"wasd0/is-rest/internal/keys"
	"wasd0/is-rest/internal/mapper"
	"wasd0/is-rest/internal/repository"
)

type BalanceService interface {
	Create(request dto.BalanceCreateRequest) (*dto.BalanceCreateResponse, *dto.ServErr)
	GetByCustomerAndCurrency(request dto.BalanceGetRequest) (*dto.BalanceGetResponse, *dto.ServErr)
	GetById(id int64) (*dto.BalanceGetResponse, *dto.ServErr)
}

type balanceServiceImpl struct {
	balanceRepository repository.BalanceRepository
	currencyService   CurrencyService
}

func (b *balanceServiceImpl) Create(request dto.BalanceCreateRequest) (*dto.BalanceCreateResponse, *dto.ServErr) {
	var currencyCode = keys.CurrencyRubCode
	if request.CurrencyCode != nil {
		currencyCode = *request.CurrencyCode
	}
	if currency, err := b.currencyService.GetByCode(currencyCode); err != nil {
		return nil, err
	} else {
		if isExists, existsErr := b.balanceRepository.ExistsByCustomerAndCurrency(request.CustomerId, currency.ID); existsErr != nil {
			return nil, dto.InternalError(existsErr)
		} else if isExists {
			return nil, dto.BadRequest(nil, "balance already exists")
		}
		balance := model.Balances{
			CustomerID: request.CustomerId,
			CurrencyID: currency.ID,
		}
		if saveErr := b.balanceRepository.Save(&balance); saveErr != nil {
			return nil, dto.InternalError(saveErr)
		}
		return mapper.BalanceToCreateResponse(balance, *currency), nil
	}
}

func (b *balanceServiceImpl) GetByCustomerAndCurrency(request dto.BalanceGetRequest) (*dto.BalanceGetResponse, *dto.ServErr) {
	var currencyCode = keys.CurrencyRubCode
	if request.CurrencyCode != nil {
		currencyCode = *request.CurrencyCode
	}
	if currency, err := b.currencyService.GetByCode(currencyCode); err != nil {
		return nil, err
	} else {
		if request.CustomerId != nil {
			if balance, balanceErr := b.balanceRepository.FindByCustomerAndCurrency(*request.CustomerId, currency.ID); balanceErr != nil {
				if errors.Is(balanceErr, qrm.ErrNoRows) {
					return nil, dto.BadRequest(balanceErr, "balance not found by customer and currency")
				} else {
					return nil, dto.InternalError(balanceErr)
				}
			} else {
				return mapper.BalanceToGetResponse(*balance, *currency), nil
			}
		} else {
			if balance, balanceErr := b.balanceRepository.FindByCustomerTelegramIdAndCurrency(*request.TelegramId, currency.ID); balanceErr != nil {
				if errors.Is(balanceErr, qrm.ErrNoRows) {
					return nil, dto.BadRequest(balanceErr, "balance not found by customer and currency")
				} else {
					return nil, dto.InternalError(balanceErr)
				}
			} else {
				return mapper.BalanceToGetResponse(*balance, *currency), nil
			}
		}
	}
}

func (b *balanceServiceImpl) GetById(id int64) (*dto.BalanceGetResponse, *dto.ServErr) {
	if balance, err := b.balanceRepository.FindById(id); err != nil {
		if errors.Is(err, qrm.ErrNoRows) {
			return nil, dto.NotFound(fmt.Sprintf("balance not found by id %v", id))
		} else {
			return nil, dto.InternalError(err)
		}
	} else {
		if currency, err := b.currencyService.GetById(balance.CurrencyID); err != nil {
			return nil, err
		} else {
			return mapper.BalanceToGetResponse(*balance, *currency), nil
		}
	}
}

func InitBalanceService(repository repository.BalanceRepository, currencyService CurrencyService) BalanceService {
	return &balanceServiceImpl{
		balanceRepository: repository,
		currencyService:   currencyService,
	}
}
