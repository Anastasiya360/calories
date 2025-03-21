package org.example.calories.service;


import org.example.calories.dto.MealCalorieDto;
import org.example.calories.dto.MealDataDto;
import org.example.calories.entity.Meal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MealService {

    /**
     * Добавление приема пищи
     *
     * @return прием пищи
     */
    Meal create(Meal meal);

    /**
     * Получение суммы всех калорий и приемов пищи
     */
    MealCalorieDto findMealsAndCaloriesByDate(Integer userId, LocalDate date);

    /**
     * Проверка уложился ли пользователь в норму калорий
     */
    Boolean checkCalorieNorm(Integer userId, LocalDate date);

    /**
     * Получение истории питания по дням
     */
    List<MealDataDto> historyOfMeals(Integer userId);

}
