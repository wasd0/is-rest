package repoProvider

import (
	"database/sql"
	"errors"
	"wasd0/is-rest/internal/repository"
	"wasd0/is-rest/pkg/logger"
)

var isInitialized = false

type RepoProvider struct {
	db *sql.DB

	country  repository.CountryRepository
	customer repository.CustomerRepository
}

func Init(db *sql.DB) *RepoProvider {
	isInitialized = true

	return &RepoProvider{db: db}
}

func (r *RepoProvider) Storage() *sql.DB {
	if !isInitialized {
		logger.Log().Fatal(errors.New("repo provider init error"), "Provider is not initialized")
	}

	return r.db
}

func (r *RepoProvider) CountryRepository() repository.CountryRepository {
	if r.country != nil {
		return r.country
	}

	r.country = repository.InitCountry(r.Storage())
	return r.country
}

func (r *RepoProvider) CustomerRepo() repository.CustomerRepository {
	if r.customer != nil {
		return r.customer
	}

	r.customer = repository.InitCustomerRepo(r.Storage())
	return r.customer
}
