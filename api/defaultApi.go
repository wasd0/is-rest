package api

import (
	"net/http"
	"wasd0/is-rest/api/dto"
)

type DefaultApi struct {
}

func NewDefaultApi() *DefaultApi {
	return &DefaultApi{}
}

func (api *DefaultApi) NotFound(w http.ResponseWriter, r *http.Request) {
	RenderError(w, r, dto.NotFound("page not found"))
	return
}

func (api *DefaultApi) MethodNotAllowed(w http.ResponseWriter, r *http.Request) {
	RenderError(w, r, dto.MethodNotAllowed("Method is not allowed"))
	return
}
