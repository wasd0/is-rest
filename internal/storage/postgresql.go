package storage

import (
	"context"
	"database/sql"
	_ "github.com/jackc/pgx/v5/stdlib"
	"log"
	"os"
	"wasd0/is-rest/pkg/app"
	"wasd0/is-rest/pkg/logger"
)

const (
	DbDriver = "pgx"
	dbUrl    = "DB_URL"
)

type PgStorage struct {
	Db *sql.DB
}

func MustOpenPostgres() (*PgStorage, app.Callback) {
	dbUrl := os.Getenv(dbUrl)

	db, err := sql.Open(DbDriver, dbUrl)

	if err != nil {
		logger.Log().Fatal(err, "Failed to open connection to database")
	}

	if err := db.Ping(); err != nil {
		log.Fatal(err, "postgres ping failed")
	}

	return &PgStorage{Db: db}, func(ctx context.Context) error {
		logger.Log().Info("Database closing...")
		return db.Close()
	}
}
