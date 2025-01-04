create table if not exists customers
(
    id          bigserial primary key,
    telegram_id bigint unique,
    blocked     boolean     not null default false,
    create_date timestamptz not null default now(),
    country_id  int         not null references countries (id)
);