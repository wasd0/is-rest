FROM golang:1.23.1 AS builder

ARG CGO_ENABLED=0
WORKDIR /app

COPY go.mod go.sum ./
RUN go mod download
COPY /. .
COPY .env.prod .env

RUN go build -o main.out cmd/is-rest/main.go

FROM alpine

COPY --from=builder /app/main.out /main.out
COPY --from=builder /app/.env.prod /.env
COPY --from=builder /app/config /config
COPY --from=builder /app/database/migrations /database/migrations

CMD ["apk add --no-cache ca-certificates"]
ENTRYPOINT ["/main.out"]