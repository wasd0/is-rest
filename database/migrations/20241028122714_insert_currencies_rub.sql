-- +goose Up
-- +goose StatementBegin
insert into currencies (code, dimension)
values ('RUB', 2);
-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
SELECT 'down SQL query';
-- +goose StatementEnd
