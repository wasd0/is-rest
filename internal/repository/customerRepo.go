package repository

import (
	"database/sql"
	. "github.com/go-jet/jet/v2/postgres"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	. "wasd0/is-rest/internal/entity/.gen/is/public/table"
)

type CustomerRepository interface {
	Save(entity *model.Customers) error
	FindByTelegramId(id int64) (*model.Customers, error)
	FindById(id int64) (*model.Customers, error)
}

type CustomerRepositoryImpl struct {
	db *sql.DB
}

func (c *CustomerRepositoryImpl) Save(entity *model.Customers) error {
	stmt :=
		Customers.INSERT(Customers.MutableColumns).
			MODEL(entity).
			RETURNING(Customers.AllColumns)
	if err := stmt.Query(c.db, entity); err != nil {
		return err
	}

	return nil
}

func (c *CustomerRepositoryImpl) FindByTelegramId(id int64) (*model.Customers, error) {
	var customer model.Customers

	stmt := SELECT(Customers.AllColumns).
		FROM(Customers).
		WHERE(Customers.TelegramID.EQ(Int(id)))
	if err := stmt.Query(c.db, &customer); err != nil {
		return nil, err
	}

	return &customer, nil
}

func (c *CustomerRepositoryImpl) FindById(id int64) (*model.Customers, error) {
	var customer model.Customers

	stmt := SELECT(Customers.AllColumns).
		FROM(Customers).
		WHERE(Customers.ID.EQ(Int(id)))
	if err := stmt.Query(c.db, &customer); err != nil {
		return nil, err
	}

	return &customer, nil
}

func InitCustomerRepo(db *sql.DB) CustomerRepository {
	return &CustomerRepositoryImpl{db: db}
}
