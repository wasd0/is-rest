package api

import (
	"errors"
	"github.com/go-chi/chi/v5"
	"github.com/go-chi/render"
	"net/http"
	"strconv"
	"wasd0/is-rest/api/dto"
	"wasd0/is-rest/internal/service"
)

type BalanceApi struct {
	balanceService service.BalanceService
}

func NewBalanceApi(balanceService service.BalanceService) *BalanceApi {
	return &BalanceApi{balanceService: balanceService}
}

func (c *BalanceApi) Register() (string, func(router chi.Router)) {
	return "/api/v1/balance", c.Handle
}

func (c *BalanceApi) Handle(router chi.Router) {
	router.Post("/", c.create)
	router.Get("/{id}", c.getById)
	router.Get("/", c.getByCustomer)
}

func (c *BalanceApi) create(w http.ResponseWriter, r *http.Request) {
	request := dto.BalanceCreateRequest{}

	if err := render.Bind(r, &request); err != nil {
		RenderError(w, r, dto.BadRequest(err, "failed get balance request"))
		return
	}

	if response, err := c.balanceService.Create(request); err != nil {
		RenderError(w, r, err)
	} else {
		restResponse := dto.RestResponse[dto.BalanceCreateResponse]{Data: response}
		Render(w, r, &restResponse)
	}
}

func (c *BalanceApi) getByCustomer(w http.ResponseWriter, r *http.Request) {
	request := dto.BalanceGetRequest{}
	customerId := r.URL.Query().Get("customerId")
	telegramId := r.URL.Query().Get("telegramId")
	currency := r.URL.Query().Get("currency")
	if currency != "" {
		request.CurrencyCode = &currency
	}
	if customerId == "" && telegramId == "" {
		RenderError(w, r, dto.BadRequest(errors.New("missing telegram id or customer id"), "missing customer id or telegram id"))
		return
	}
	if customerId != "" {
		if id, err := strconv.ParseInt(customerId, 10, 64); err != nil {
			RenderError(w, r, dto.BadRequest(err, "invalid customer id"))
		} else {
			request.CustomerId = &id
		}
	}
	if telegramId != "" {
		if id, err := strconv.ParseInt(telegramId, 10, 64); err != nil {
			RenderError(w, r, dto.BadRequest(err, "invalid telegram id"))
		} else {
			request.TelegramId = &id
		}
	}
	if response, err := c.balanceService.GetByCustomerAndCurrency(request); err != nil {
		RenderError(w, r, err)
	} else {
		restResponse := dto.RestResponse[dto.BalanceGetResponse]{Data: response}
		Render(w, r, &restResponse)
	}
}

func (c *BalanceApi) getById(w http.ResponseWriter, r *http.Request) {
	idStr := chi.URLParam(r, "id")
	id, parseErr := strconv.ParseInt(idStr, 10, 64)

	if parseErr != nil {
		RenderError(w, r, dto.InternalError(parseErr))
		return
	}

	if response, err := c.balanceService.GetById(id); err != nil {
		RenderError(w, r, err)
	} else {
		restResponse := dto.RestResponse[dto.BalanceGetResponse]{Data: response}
		Render(w, r, &restResponse)
	}
}
