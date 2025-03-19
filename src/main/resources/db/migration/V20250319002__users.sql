create table if not exists calories.users
(
    id           bigserial
        constraint users_pk
            primary key,
    name         varchar(255) not null,
    email        varchar(255) not null,
    age          smallint     not null,
    weight       smallint     not null,
    height       smallint     not null,
    goal         varchar(50)  not null,
    calorie_norm integer      not null

);