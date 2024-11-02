package storage

import (
	"context"
	"database/sql"
	_ "github.com/jackc/pgx/v5/stdlib"
	"github.com/wasd0/is-common/pkg/app"
	"github.com/wasd0/is-common/pkg/logger"
	"log"
	"os"
	"wasd0/is-rest/internal/keys"
)

const (
	DbDriver = "pgx"
)

type PgStorage struct {
	Db *sql.DB
}

func MustOpenPostgres() (*PgStorage, app.Callback) {
	dbUrl := os.Getenv(keys.EnvDbUrl)

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
