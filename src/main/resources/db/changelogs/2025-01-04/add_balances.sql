create table if not exists balances
(
    id          bigserial primary key,
    customer_id bigint not null references customers (id),
    sum         bigint not null default 0,
    currency_id int    not null references currencies (id),
    constraint unique_customer_currency unique (customer_id, currency_id)
);