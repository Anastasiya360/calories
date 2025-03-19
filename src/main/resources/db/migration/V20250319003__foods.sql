create table if not exists calories.foods
(
    id                 bigserial
        constraint foods_pk
            primary key,
    name               varchar(255) not null,
    amount_of_calories integer      not null,
    proteins           smallint     not null,
    fats               smallint     not null,
    carbohydrates      smallint     not null

);