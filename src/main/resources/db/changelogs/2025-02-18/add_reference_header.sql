create table if not exists ref_headers
(
    id        int primary key,
    name      varchar(100),
    is_active boolean default false
)