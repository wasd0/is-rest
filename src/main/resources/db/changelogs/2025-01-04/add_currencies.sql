create table if not exists currencies
(
    id        serial primary key,
    code      char(3) not null unique,
    dimension int     not null default 2,
    min_value bigint           default 0,
    max_value bigint
);

insert into currencies (code, dimension)
values ('RUB', 2);