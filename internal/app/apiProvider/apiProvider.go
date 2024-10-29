package apiProvider

import (
	"wasd0/is-rest/api"
	"wasd0/is-rest/internal/app/serviceProvider"
)

type ApiProvider struct {
	services *serviceProvider.ServiceProvider

	defaultApi *api.DefaultApi
	customer   *api.CustomerApi
	balance    *api.BalanceApi
}

func Init(services *serviceProvider.ServiceProvider) *ApiProvider {
	return &ApiProvider{services: services}
}

func (ap *ApiProvider) DefaultApi() *api.DefaultApi {
	if ap.defaultApi != nil {
		return ap.defaultApi
	}

	ap.defaultApi = api.NewDefaultApi()
	return ap.defaultApi
}

func (ap *ApiProvider) CustomerApi() *api.CustomerApi {
	if ap.customer != nil {
		return ap.customer
	}

	ap.customer = api.NewCustomerApi(ap.services.CustomerService())
	return ap.customer
}

func (ap *ApiProvider) BalanceApi() *api.BalanceApi {
	if ap.balance != nil {
		return ap.balance
	}

	ap.balance = api.NewBalanceApi(ap.services.BalanceService())
	return ap.balance
}
