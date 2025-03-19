package org.example.calories.controller.impl;


import lombok.RequiredArgsConstructor;
import org.example.calories.controller.FoodController;
import org.example.calories.entity.Food;
import org.example.calories.service.FoodService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodControllerImpl implements FoodController {

    private final FoodService foodService;

    @Override
    public Food create(Food food) {
        return foodService.create(food);
    }
}
