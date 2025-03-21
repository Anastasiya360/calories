package org.example.calories.repository;

import jakarta.persistence.Tuple;
import org.example.calories.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Query(nativeQuery = true, value = """
            select count (distinct m.id), sum(amount_of_calories)
            from meals m
                     join calories.meals_foods mf on m.id = mf.meal_id
                     join calories.foods f on f.id = mf.food_id
            where m.user_id = :userId
              and m.date_time_of_eating > :dateFrom
              and m.date_time_of_eating < :dateTo""")
    Tuple findMealsCountAndCaloriesByUserIdAndDate(Integer userId, LocalDate dateFrom, LocalDate dateTo);

    @Query(nativeQuery = true, value = """
            select *
            from meals m
            where m.user_id = :userId
              and m.date_time_of_eating > :dateFrom
              and m.date_time_of_eating < :dateTo""")
    List<Meal> findAllByUserIdAndDate(Integer userId, LocalDate dateFrom, LocalDate dateTo);

    @Query(nativeQuery = true, value = """
            select distinct m.date_time_of_eating :: date
            from meals m
            where user_id = :userId""")
    List<Date> findAllDateByUserId(Integer userId);
}
