package org.example.calories.controller.impl;


import lombok.RequiredArgsConstructor;
import org.example.calories.controller.MealController;
import org.example.calories.dto.MealCalorieDto;
import org.example.calories.dto.MealDataDto;
import org.example.calories.entity.Meal;
import org.example.calories.service.MealService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealControllerImpl implements MealController {

    private final MealService mealService;

    @Override
    public Meal create(Meal meal) {
        return mealService.create(meal);
    }

    @Override
    public MealCalorieDto findMealsAndCaloriesByDate(Integer userId, LocalDate date) {
        return mealService.findMealsAndCaloriesByDate(userId, date);
    }

    @Override
    public Boolean checkCalorieNorm(Integer userId, LocalDate date) {
        return mealService.checkCalorieNorm(userId, date);
    }

    @Override
    public List<MealDataDto> historyOfMeals(Integer userId) {
        return mealService.historyOfMeals(userId);
    }
}
