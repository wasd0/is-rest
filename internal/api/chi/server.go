package chi

import (
	"errors"
	"fmt"
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"github.com/go-chi/cors"
	"net/http"
	"wasd0/is-rest/internal/app/apiProvider"
	"wasd0/is-rest/pkg/app"
	"wasd0/is-rest/pkg/config"
	"wasd0/is-rest/pkg/logger"
)

type Server struct {
	server *http.Server
}

func SetupServer(cfg *config.Config, provider *apiProvider.ApiProvider, formatter *LogFormatterImpl) *Server {
	mux := chi.NewRouter()
	addr := fmt.Sprintf("%s:%s", cfg.Server.Host, cfg.Server.Port)
	server := &http.Server{Addr: addr, Handler: mux}

	setUpMiddlewares(cfg, mux, formatter)
	setUpRouters(mux, provider)

	return &Server{
		server: server,
	}
}

func (s *Server) MustRun() app.Callback {
	go func() {
		if err := s.server.ListenAndServe(); err != nil && !errors.Is(err, http.ErrServerClosed) {
			logger.Log().Fatal(err, "http server start failed")
		}
	}()

	return s.server.Shutdown
}

func setUpMiddlewares(cfg *config.Config, mux *chi.Mux, formatter *LogFormatterImpl) {
	mux.Use(middleware.RequestLogger(formatter))
	mux.Use(middleware.Timeout(cfg.Server.IdleTimeout))
	mux.Use(cors.New(cors.Options{
		AllowedOrigins: []string{"https://*", "http://localhost:*"},
		AllowedMethods: []string{
			http.MethodHead,
			http.MethodGet,
			http.MethodPost,
			http.MethodPut,
			http.MethodPatch,
			http.MethodDelete,
		},
		AllowedHeaders:   []string{"*"},
		AllowCredentials: true,
	}).Handler)
}

func setUpRouters(mux *chi.Mux, provider *apiProvider.ApiProvider) {
	mux.NotFound(provider.DefaultApi().NotFound)
	mux.MethodNotAllowed(provider.DefaultApi().MethodNotAllowed)
	mux.Route(provider.CustomerApi().Register())
}
