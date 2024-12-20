-- +goose Up
-- +goose StatementBegin
create table if not exists countries
(
    id   serial primary key,
    code int        not null,
    iso  varchar(2) not null,
    name varchar(50),
    unique (code, iso)
);

INSERT INTO countries (code, iso, name)
VALUES (374, 'AM', 'Armenia'),
       (994, 'AZ', 'Azerbaijan'),
       (375, 'BY', 'Belarus'),
       (7, 'KZ', 'Kazakhstan'),
       (996, 'KG', 'Kyrgyzstan'),
       (373, 'MD', 'Moldova'),
       (7, 'RU', 'Russia'),
       (992, 'TJ', 'Tajikistan'),
       (993, 'TM', 'Turkmenistan'),
       (998, 'UZ', 'Uzbekistan'),
       (380, 'UA', 'Ukraine'),
       (43, 'AT', 'Austria'),
       (32, 'BE', 'Belgium'),
       (359, 'BG', 'Bulgaria'),
       (357, 'CY', 'Cyprus'),
       (45, 'DK', 'Denmark'),
       (372, 'EE', 'Estonia'),
       (20, 'FI', 'Finland'),
       (33, 'FR', 'France'),
       (49, 'DE', 'Germany'),
       (30, 'GR', 'Greece'),
       (354, 'IS', 'Iceland'),
       (353, 'IE', 'Ireland'),
       (39, 'IT', 'Italy'),
       (371, 'LV', 'Latvia'),
       (423, 'LI', 'Liechtenstein'),
       (370, 'LT', 'Lithuania'),
       (352, 'LU', 'Luxembourg'),
       (356, 'MT', 'Malta'),
       (31, 'NL', 'Netherlands'),
       (47, 'NO', 'Norway'),
       (48, 'PL', 'Poland'),
       (351, 'PT', 'Portugal'),
       (40, 'RO', 'Romania'),
       (421, 'SK', 'Slovakia'),
       (386, 'SI', 'Slovenia'),
       (41, 'CH', 'Switzerland'),
       (44, 'GB', 'United Kingdom'),
       (1, 'US', 'United States')
on conflict do nothing;
-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
SELECT 'down SQL query';
-- +goose StatementEnd
