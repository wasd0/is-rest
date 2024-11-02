package api

import (
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/render"
	"github.com/wasd0/is-common/pkg/logger"
	"net/http"
	"wasd0/is-rest/api/dto"
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

func Render(w http.ResponseWriter, r *http.Request, v render.Renderer) {
	if err := render.Render(w, r, v); err != nil {
		RenderError(w, r, dto.InternalError(err))
	}
}
