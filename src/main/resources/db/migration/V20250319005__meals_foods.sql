create table if not exists calories.meals_foods
(
    food_id bigint not null,
    meal_id bigint not null,
    constraint meals_foods_pkey primary key (meal_id, food_id),
    constraint meals_foods_food_fk foreign key (food_id)
        references calories.foods (id) match simple
        on update cascade
        on delete cascade,
    constraint  meals_foods_meals_fk foreign key (meal_id)
        references calories.meals (id) match simple
        on update cascade
        on delete cascade
);
create index if not exists meals_foods_food_ix on calories.meals_foods (food_id);
create index if not exists meals_foods_meals_ix on calories.meals_foods (meal_id);