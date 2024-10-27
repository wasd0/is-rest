package serviceProvider

import (
	"wasd0/is-rest/internal/app/repoProvider"
	"wasd0/is-rest/internal/service"
)

type ServiceProvider struct {
	repos *repoProvider.RepoProvider

	country  service.CountryService
	customer service.CustomerService
}

func Init(repos *repoProvider.RepoProvider) *ServiceProvider {
	return &ServiceProvider{repos: repos}
}

func (sp *ServiceProvider) CountryService() service.CountryService {
	if sp.country != nil {
		return sp.country
	}

	country := service.InitCountryService(sp.repos.CountryRepository())
	sp.country = country
	return country
}

func (sp *ServiceProvider) CustomerService() service.CustomerService {
	if sp.customer != nil {
		return sp.customer
	}

	customer := service.InitCustomerService(sp.repos.CustomerRepo(), sp.CountryService())
	sp.customer = customer
	return customer
}
