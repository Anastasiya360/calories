create table if not exists calories.meals
(
    id                  bigserial
        constraint meals_pk
            primary key,
    name                varchar(255) not null,
    user_id             bigint       not null,
    constraint meals_user_id_fk
        foreign key (user_id) references calories.users (id)
            match simple
            on update cascade
            on delete cascade,
    date_time_of_eating timestamp without time zone default now()

);
create index meals_user_id_ix on calories.meals (user_id);
