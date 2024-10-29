package repository

import (
	"database/sql"
	. "github.com/go-jet/jet/v2/postgres"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	. "wasd0/is-rest/internal/entity/.gen/is/public/table"
)

type CurrencyRepository interface {
	GetByCode(code string) (*model.Currencies, error)
	GetById(id int32) (*model.Currencies, error)
}

type currencyRepositoryImpl struct {
	db *sql.DB
}

func (c *currencyRepositoryImpl) GetById(id int32) (*model.Currencies, error) {
	var currency model.Currencies
	stmt := SELECT(Currencies.AllColumns).FROM(Currencies).WHERE(Currencies.ID.EQ(Int32(id)))
	if err := stmt.Query(c.db, &currency); err != nil {
		return nil, err
	}
	return &currency, nil
}

func (c *currencyRepositoryImpl) GetByCode(code string) (*model.Currencies, error) {
	var currency model.Currencies
	stmt := SELECT(Currencies.AllColumns).FROM(Currencies).WHERE(Currencies.Code.EQ(String(code)))
	if err := stmt.Query(c.db, &currency); err != nil {
		return nil, err
	}
	return &currency, nil
}

func InitCurrencyRepository(db *sql.DB) CurrencyRepository {
	return &currencyRepositoryImpl{db: db}
}
