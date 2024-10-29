package repository

import (
	"database/sql"
	"errors"
	. "github.com/go-jet/jet/v2/postgres"
	"github.com/go-jet/jet/v2/qrm"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	. "wasd0/is-rest/internal/entity/.gen/is/public/table"
)

type BalanceRepository interface {
	Save(entity *model.Balances) error
	FindById(id int64) (*model.Balances, error)
	FindByCustomerAndCurrency(customerId int64, currencyId int32) (*model.Balances, error)
	FindByCustomerTelegramIdAndCurrency(telegramId int64, currencyId int32) (*model.Balances, error)
	ExistsByCustomerAndCurrency(customerId int64, currencyId int32) (bool, error)
}

type BalanceRepositoryImpl struct {
	db *sql.DB
}

func (b *BalanceRepositoryImpl) FindByCustomerTelegramIdAndCurrency(telegramId int64, currencyId int32) (*model.Balances, error) {
	var balance model.Balances
	stmt := SELECT(Balances.AllColumns).
		FROM(Balances.INNER_JOIN(Customers, Balances.CustomerID.EQ(Customers.ID).
			AND(Customers.TelegramID.EQ(Int64(telegramId))))).
		WHERE(Balances.CurrencyID.EQ(Int32(currencyId)))
	if err := stmt.Query(b.db, &balance); err != nil {
		return nil, err
	}
	return &balance, nil
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

func (b *BalanceRepositoryImpl) ExistsByCustomerAndCurrency(customerId int64, currencyId int32) (bool, error) {
	var id struct {
		int64
	}
	stmt := SELECT(Balances.ID).
		FROM(Balances).
		WHERE(Balances.CustomerID.EQ(Int64(customerId)).
			AND(Balances.CurrencyID.EQ(Int32(currencyId))))
	if err := stmt.Query(b.db, &id); err != nil {
		if errors.Is(err, qrm.ErrNoRows) {
			return false, nil
		} else {
			return false, err
		}
	}
	return true, nil
}

func InitBalanceRepository(db *sql.DB) BalanceRepository {
	return &BalanceRepositoryImpl{db: db}
}
