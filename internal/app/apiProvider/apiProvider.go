package apiProvider

import (
	"wasd0/is-rest/api"
	"wasd0/is-rest/internal/app/serviceProvider"
)

type ApiProvider struct {
	services *serviceProvider.ServiceProvider

	defaultApi  *api.DefaultApi
	customerApi *api.CustomerApi
}

func Init(services *serviceProvider.ServiceProvider) *ApiProvider {
	return &ApiProvider{services: services}
}

func (ap *ApiProvider) DefaultApi() *api.DefaultApi {
	if ap.defaultApi != nil {
		return ap.defaultApi
	}

	dApi := api.NewDefaultApi()
	ap.defaultApi = dApi
	return ap.defaultApi
}

func (ap *ApiProvider) CustomerApi() *api.CustomerApi {
	if ap.customerApi != nil {
		return ap.customerApi
	}

	ap.customerApi = api.NewCustomerApi(ap.services.CustomerService())
	return ap.customerApi
}
