package repository

import (
	"database/sql"
	. "github.com/go-jet/jet/v2/postgres"
	"wasd0/is-rest/internal/entity/.gen/is/public/model"
	. "wasd0/is-rest/internal/entity/.gen/is/public/table"
)

type CountryRepository interface {
	FindByCodeAndIso(code int64, iso string) (*model.Countries, error)
	FindById(id int32) (*model.Countries, error)
}

type CountryRepositoryImpl struct {
	db *sql.DB
}

func InitCountry(db *sql.DB) CountryRepository {
	return &CountryRepositoryImpl{db: db}
}

func (c *CountryRepositoryImpl) FindByCodeAndIso(code int64, iso string) (*model.Countries, error) {
	var country model.Countries

	stmt := SELECT(Countries.AllColumns).
		FROM(Countries).
		WHERE(Countries.Code.EQ(Int(code)).
			AND(Countries.Iso.EQ(String(iso))))
	if err := stmt.Query(c.db, &country); err != nil {
		return nil, err
	}

	return &country, nil
}

func (c *CountryRepositoryImpl) FindById(id int32) (*model.Countries, error) {
	var country model.Countries

	stmt := SELECT(Countries.AllColumns).
		FROM(Countries).
		WHERE(Countries.ID.EQ(Int32(id)))
	if err := stmt.Query(c.db, &country); err != nil {
		return nil, err
	}

	return &country, nil
}
