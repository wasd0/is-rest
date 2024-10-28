-- +goose Up
-- +goose StatementBegin
create table if not exists balances
(
    id          bigserial primary key,
    customer_id bigint not null references customers (id),
    sum         bigint not null default 0,
    currency_id int    not null references currencies (id)
);
-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
SELECT 'down SQL query';
-- +goose StatementEnd
