package org.example.calories.service;


import org.example.calories.entity.Food;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {

    /**
     * Добавление блюда
     *
     * @return добавленное блюдо
     */
    Food create(Food food);
}
