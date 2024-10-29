package repository

import (
	"database/sql"
	. "github.com/go-jet/jet/v2/postgres"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	. "wasd0/is-rest/internal/entity/.gen/is/public/table"
)

type BalanceRepository interface {
	Save(entity *model.Balances) error
	FindById(id int64) (*model.Balances, error)
	FindByCustomerAndCurrency(customerId int64, currencyId int32) (*model.Balances, error)
}

type BalanceRepositoryImpl struct {
	db *sql.DB
}

func (b *BalanceRepositoryImpl) Save(entity *model.Balances) error {
	stmt :=
		Balances.INSERT(Balances.MutableColumns).
			MODEL(entity).
			RETURNING(Balances.AllColumns)
	if err := stmt.Query(b.db, entity); err != nil {
		return err
	}

	return nil

}

func (b *BalanceRepositoryImpl) FindById(id int64) (*model.Balances, error) {
	var balance model.Balances
	stmt := SELECT(Balances.AllColumns).FROM(Balances).WHERE(Balances.ID.EQ(Int64(id)))
	if err := stmt.Query(b.db, &balance); err != nil {
		return nil, err
	}
	return &balance, nil
}

func (b *BalanceRepositoryImpl) FindByCustomerAndCurrency(customerId int64, currencyId int32) (*model.Balances, error) {
	var balance model.Balances
	stmt := SELECT(Balances.AllColumns).
		FROM(Balances).
		WHERE(Balances.CustomerID.EQ(Int64(customerId)).
			AND(Balances.CurrencyID.EQ(Int32(currencyId))))
	if err := stmt.Query(b.db, &balance); err != nil {
		return nil, err
	}
	return &balance, nil
}

func InitBalanceRepository(db *sql.DB) BalanceRepository {
	return &BalanceRepositoryImpl{db: db}
}
