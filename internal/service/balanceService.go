package service

import "wasd0/is-rest/api/dto"

type BalanceService interface {
	Create(request dto.BalanceCreateRequest) (dto.BalanceCreateResponse, *dto.ServErr)
	GetByCustomer(request dto.BalanceGetRequest) (dto.BalanceGetResponse, *dto.ServErr)
	GetById(id int64) (dto.BalanceGetResponse, *dto.ServErr)
}
