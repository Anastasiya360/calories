package org.example.calories.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.calories.entity.Food;
import org.example.calories.exception.ApiException;
import org.example.calories.repository.FoodRepository;
import org.example.calories.service.FoodService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food create(Food food) {
        food.setId(null);
        check(food);
        return foodRepository.save(food);
    }

    private void check(Food food) {
        if (food.getName() == null || food.getName().isBlank()) {
            throw new ApiException("Имя не передано", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (food.getAmountOfCalories() == null) {
            throw new ApiException("Количество калорий не передано", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (food.getProteins() == null) {
            throw new ApiException("Количество белка не указано", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (food.getFats() == null) {
            throw new ApiException("Количество жиров не указано", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (food.getCarbohydrates() == null) {
            throw new ApiException("Количество углеводов не указано", HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
