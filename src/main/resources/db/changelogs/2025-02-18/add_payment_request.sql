create table if not exists payment_requests
(
    id          uuid primary key default uuid_generate_v4(),
    external_id varchar,
    sum         bigint,
    balance_id  bigint not null references balances (id),
    currency_id int references currencies (id),
    status      bigint references refs (id),
    created_at  timestamptz,
    updated_at  timestamptz
)