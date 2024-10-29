package service

import (
	"errors"
	"github.com/go-jet/jet/v2/qrm"
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	"wasd0/is-rest/internal/keys"
	"wasd0/is-rest/internal/mapper"
	"wasd0/is-rest/internal/repository"
	"wasd0/is-rest/pkg/logger"
	"wasd0/is-rest/pkg/utils/clock"
)

type CustomerService interface {
	CreateOrGet(request dto.CustomerGetRequest) (dto.CustomerGetResponse, *dto.ServErr)
	FindById(id int64) (*model.Customers, error)
}

type CustomerServiceImpl struct {
	customerRepo   repository.CustomerRepository
	countryService CountryService
}

func InitCustomerService(customerRepo repository.CustomerRepository, countryService CountryService) CustomerService {
	return &CustomerServiceImpl{
		customerRepo:   customerRepo,
		countryService: countryService,
	}
}

func (c *CustomerServiceImpl) CreateOrGet(request dto.CustomerGetRequest) (dto.CustomerGetResponse, *dto.ServErr) {
	var customer *model.Customers
	var findErr error

	if request.Id != nil {
		customer, findErr = c.FindById(*request.Id)
	} else if request.TelegramId != nil {
		customer, findErr = c.customerRepo.FindByTelegramId(*request.TelegramId)
	}

	if findErr != nil && !errors.Is(findErr, qrm.ErrNoRows) {
		logger.Log().Error(findErr, "customer find error")
		return dto.CustomerGetResponse{}, dto.InternalError(findErr)
	} else if customer != nil {
		return c.sendFindResponse(customer)
	}

	country, err := c.countryService.FindByCodeAndIso(keys.CountryCodeRu, keys.CountryIsoRu)

	if err != nil {
		logger.Log().Error(err, "customer find error")
		country = &model.Countries{}
	}

	customer = &model.Customers{
		CreateDate: clock.Now(),
		CountryID:  country.ID,
		TelegramID: request.TelegramId,
	}

	if err := c.customerRepo.Save(customer); err != nil {
		return dto.CustomerGetResponse{}, dto.InternalError(err)
	}

	return c.sendFindResponse(customer)
}

func (c *CustomerServiceImpl) FindById(id int64) (*model.Customers, error) {
	return c.customerRepo.FindById(id)
}

func (c *CustomerServiceImpl) sendFindResponse(customer *model.Customers) (dto.CustomerGetResponse, *dto.ServErr) {
	country, err := c.countryService.FindById(customer.CountryID)
	if err != nil && !errors.Is(err, qrm.ErrNoRows) {
		logger.Log().Error(err, "customer find error")
		country = &model.Countries{Iso: ""}
	}
	return mapper.CustomerToGetResponse(*customer, country), nil
}
