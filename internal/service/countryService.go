package service

import (
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	"wasd0/is-rest/internal/repository"
)

type CountryService interface {
	FindByCodeAndIso(code int64, iso string) (*model.Countries, error)
	FindById(id int32) (*model.Countries, error)
}

type CountryServiceImpl struct {
	repo repository.CountryRepository
}

func InitCountryService(repo repository.CountryRepository) CountryService {
	return &CountryServiceImpl{repo: repo}
}

func (c *CountryServiceImpl) FindByCodeAndIso(code int64, iso string) (*model.Countries, error) {
	return c.repo.FindByCodeAndIso(code, iso)
}

func (c *CountryServiceImpl) FindById(id int32) (*model.Countries, error) {
	return c.repo.FindById(id)
}
