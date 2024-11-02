package app

import (
	"context"
	"github.com/wasd0/is-common/pkg/app"
	"github.com/wasd0/is-common/pkg/config"
	"github.com/wasd0/is-common/pkg/logger"
	"github.com/wasd0/is-common/pkg/logger/zero"
	"os"
	"os/signal"
	"syscall"
	"wasd0/is-rest/internal/api/chi"
	"wasd0/is-rest/internal/app/apiProvider"
	"wasd0/is-rest/internal/app/repoProvider"
	"wasd0/is-rest/internal/app/serviceProvider"
	"wasd0/is-rest/internal/storage"
)

func Startup() {
	ctx, stop := signal.NotifyContext(context.Background(), syscall.SIGINT,
		syscall.SIGTERM, syscall.SIGHUP, syscall.SIGQUIT, os.Interrupt)

	defer stop()
	runServer(ctx)
}

func runServer(ctx context.Context) {
	cfg := config.MustRead()
	pg, dbCallback := storage.MustOpenPostgres()
	closer := &app.Closer{}
	_, loggerCallback := zero.MustSetUp(cfg)
	logFormatter := chi.LogFormatterImpl{}

	var (
		repos    = repoProvider.Init(pg.Db)
		services = serviceProvider.Init(repos)
		apis     = apiProvider.Init(services)
	)

	storage.Migrate(pg.Db)
	api := chi.SetupServer(cfg, apis, &logFormatter)
	serverCallback := api.MustRun()

	closer.Add(loggerCallback)
	closer.Add(dbCallback)
	closer.Add(serverCallback)

	printStartMessage(cfg)

	<-ctx.Done()

	shutdownCtx, cancel := context.WithTimeout(context.Background(), cfg.Server.Timeout)
	defer cancel()

	if err := closer.Close(shutdownCtx); err != nil {
		logger.Log().Fatal(err, "Server close failed")
	}
}

func printStartMessage(cfg *config.Config) {
	logger.Log().Info("Server  started")
	logger.Log().Infof("Host: %s", cfg.Server.Host)
	logger.Log().Infof("Port: %s", cfg.Server.Port)
	logger.Log().Infof("ENV: %s", cfg.Env)
}
