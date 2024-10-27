package api

import (
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/render"
	"net/http"
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/service"
)

type CustomerApi struct {
	customerService service.CustomerService
}

func NewCustomerApi(customerService service.CustomerService) *CustomerApi {
	return &CustomerApi{customerService: customerService}
}

func (c *CustomerApi) Register() (string, func(router chi.Router)) {
	return "/api/v1/customers", c.Handle
}

func (c *CustomerApi) Handle(router chi.Router) {
	router.Post("/", c.getOrCreate)
}

func (c *CustomerApi) getOrCreate(w http.ResponseWriter, r *http.Request) {
	request := dto.CustomerGetRequest{}

	if err := render.Bind(r, &request); err != nil {
		RenderError(w, r, dto.BadRequest(err, "failed get customer get or create request "))
		return
	}

	if response, err := c.customerService.CreateOrGet(request); err != nil {
		RenderError(w, r, err)
	} else {
		restResponse := dto.RestResponse[dto.CustomerGetResponse]{Data: &response}
		Render(w, r, &restResponse)
	}
}
