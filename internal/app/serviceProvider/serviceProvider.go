package serviceProvider

import (
	"wasd0/is-rest/internal/app/repoProvider"
	"wasd0/is-rest/internal/service"
)

type ServiceProvider struct {
	repos *repoProvider.RepoProvider

	country  service.CountryService
	customer service.CustomerService
	currency service.CurrencyService
	balance  service.BalanceService
}

func Init(repos *repoProvider.RepoProvider) *ServiceProvider {
	return &ServiceProvider{repos: repos}
}

func (sp *ServiceProvider) CountryService() service.CountryService {
	if sp.country != nil {
		return sp.country
	}
	sp.country = service.InitCountryService(sp.repos.CountryRepository())
	return sp.country
}

func (sp *ServiceProvider) CustomerService() service.CustomerService {
	if sp.customer != nil {
		return sp.customer
	}
	sp.customer = service.InitCustomerService(sp.repos.CustomerRepo(), sp.CountryService())
	return sp.customer
}

func (sp *ServiceProvider) CurrencyService() service.CurrencyService {
	if sp.currency != nil {
		return sp.currency
	}
	sp.currency = service.InitCurrencyService(sp.repos.CurrencyRepo())
	return sp.currency
}

func (sp *ServiceProvider) BalanceService() service.BalanceService {
	if sp.balance != nil {
		return sp.balance
	}
	sp.balance = service.InitBalanceService(sp.repos.BalanceRepo(), sp.CurrencyService())
	return sp.balance
}
