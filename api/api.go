package api

import (
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/render"
	"net/http"
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/pkg/logger"
)

type ChiApi interface {
	Register() (string, func(router chi.Router))
	Handle(router chi.Router)
}

func RenderError(w http.ResponseWriter, r *http.Request, servErr *dto.ServErr) {
	renderErr := render.Render(w, r, dto.NewErrRenderer(servErr))
	if renderErr != nil {
		logger.Log().Fatal(renderErr, "rendering error")
	}
}

func Render[T any](w http.ResponseWriter, r *http.Request, v render.Renderer) {
	if err := render.Render(w, r, v); err != nil {
		RenderError(w, r, dto.InternalError(err))
	}
}
