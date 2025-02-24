create table if not exists refs
(
    id        bigserial primary key,
    header_id int references ref_headers (id) not null,
    value     varchar(255),
    code      int,
    active    boolean                         not null default true,
    name      varchar(100)
)