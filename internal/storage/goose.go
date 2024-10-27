package storage

import (
	"database/sql"
	"embed"
	"github.com/pressly/goose/v3"
	"os"
	"wasd0/is-rest/pkg/logger"
)

var embedMigrations embed.FS

func Migrate(db *sql.DB) {

	if err := goose.SetDialect("postgres"); err != nil {
		logger.Log().Fatal(err, "goose.SetDialect")
	}

	migrationsPath := os.Getenv("MIGRATIONS_PATH")

	if err := goose.Up(db, migrationsPath); err != nil {
		logger.Log().Fatal(err, "goose.Up")
	}
}
