package org.example.calories.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.calories.dto.MealCalorieDto;
import org.example.calories.dto.MealDataDto;
import org.example.calories.entity.Meal;
import org.example.calories.exception.ApiException;
import org.example.calories.mapper.TuplesToDto;
import org.example.calories.repository.FoodRepository;
import org.example.calories.repository.MealRepository;
import org.example.calories.repository.UserRepository;
import org.example.calories.service.MealService;
import org.example.calories.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final UserService userService;

    @Override
    public Meal create(Meal meal) {
        meal.setId(null);
        userService.existsUser(meal.getUserId());
        checkMeals(meal);
        checkFoods(meal);
        return mealRepository.save(meal);
    }

    @Override
    public MealCalorieDto findMealsAndCaloriesByDate(Integer userId, LocalDate date) {
        userService.existsUser(userId);
        LocalDate dateTo = date.plusDays(1);
        return TuplesToDto.tupleToDto(mealRepository.findMealsCountAndCaloriesByUserIdAndDate(userId, date, dateTo));
    }

    @Override
    public Boolean checkCalorieNorm(Integer userId, LocalDate date) {
        userService.existsUser(userId);
        MealCalorieDto mealCalorieDto = findMealsAndCaloriesByDate(userId, date);
        Integer normaCaloriesForUser = userRepository.findById(userId).get().getCalorieNorm();
        Long factCalories = mealCalorieDto.getCalories();
        if (factCalories == null) {
            throw new ApiException("Записей о приемах пищи за этот день нет", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (normaCaloriesForUser >= factCalories) {
            return true;
        } else return false;
    }

    @Override
    public List<MealDataDto> historyOfMeals(Integer userId) {
        userService.existsUser(userId);
        List<Date> allDate = mealRepository.findAllDateByUserId(userId);
        if (allDate.isEmpty()) {
            throw new ApiException("Не найдены записи о приемах пищи", HttpServletResponse.SC_NOT_FOUND);
        }
        List<MealDataDto> mealDataDtos = new ArrayList<>();
        allDate.forEach(date -> {
            MealDataDto mealDataDto = new MealDataDto();
            mealDataDto.setDate(toLocalDate(date));
            mealDataDto.setMealCalorieDto(findMealsAndCaloriesByDate(userId, toLocalDate(date)));
            mealDataDtos.add(mealDataDto);
        });
        return mealDataDtos;
    }

    private void checkFoods(Meal meal) {
        meal.getFoods().forEach(food -> {
            if (food.getId() == null) {
                throw new ApiException("id блюда не передан", HttpServletResponse.SC_BAD_REQUEST);
            }
            if (!foodRepository.existsById(food.getId())) {
                throw new ApiException("Блюдо не найдено", HttpServletResponse.SC_NOT_FOUND);
            }
        });
    }

    private void checkMeals(Meal meal) {
        if (meal.getName() == null || meal.getName().isBlank()) {
            throw new ApiException("Имя не передано", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (meal.getFoods() == null || meal.getFoods().isEmpty()) {
            throw new ApiException("Блюда не переданы", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
