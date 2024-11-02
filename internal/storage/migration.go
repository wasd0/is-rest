package storage

import (
	"database/sql"
	"errors"
	"fmt"
	"github.com/pressly/goose/v3"
	"os"
	"wasd0/is-rest/internal/keys"
	"wasd0/is-rest/pkg/logger"
)

type gooseLogger struct {
}

func (g *gooseLogger) Fatalf(format string, v ...interface{}) {
	logger.Log().Fatalf(errors.New("migration failed"), fmt.Sprintf(format, v...))
}

func (g *gooseLogger) Printf(format string, v ...interface{}) {
	logger.Log().Infof(format, v...)
}

func Migrate(db *sql.DB) {

	if err := goose.SetDialect("postgres"); err != nil {
		logger.Log().Fatal(err, "goose.SetDialect")
	}

	goose.SetLogger(&gooseLogger{})

	migrationsPath := os.Getenv(keys.EnvMigrationsPath)

	if err := goose.Up(db, migrationsPath); err != nil {
		logger.Log().Fatal(err, "goose.Up")
	}
}
