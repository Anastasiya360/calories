package org.example.calories;

import jakarta.servlet.http.HttpServletResponse;
import org.example.calories.entity.Food;
import org.example.calories.entity.Meal;
import org.example.calories.entity.User;
import org.example.calories.exception.ApiException;
import org.example.calories.repository.FoodRepository;
import org.example.calories.repository.UserRepository;
import org.example.calories.service.MealService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MealTests {

    @Autowired
    private MealService mealService;

    @MockitoBean
    private FoodRepository foodRepository;

    @MockitoBean
    private UserRepository userRepository;


    @Test
    void testMealCreateUserNotFound() {
        Food food = new Food();
        food.setId(1);
        food.setName("test");
        food.setAmountOfCalories(600);
        food.setProteins((short) 5);
        food.setFats((short) 5);
        food.setCarbohydrates((short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(false);
        Meal meal = new Meal(1, "name", 2, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_NOT_FOUND, thrown.getStatusCode());
        Assertions.assertEquals("Пользователь не найден", thrown.getMessage());
    }

    @Test
    void testMealCreateUserIdIsNull() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Meal meal = new Meal(1, "name", null, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("id не передан", thrown.getMessage());
    }

    @Test
    void testMealCreateNameNull() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Meal meal = new Meal(1, null, 2, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Имя не передано", thrown.getMessage());
    }

    @Test
    void testMealCreateNameIsBlank() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Meal meal = new Meal(1, " ", 2, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Имя не передано", thrown.getMessage());
    }

    @Test
    void testMealCreateFoodNull() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Meal meal = new Meal(1, "name", 2, localDate, null);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Блюда не переданы", thrown.getMessage());
    }

    @Test
    void testMealCreateFoodIsBlank() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Meal meal = new Meal(1, "name", 2, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Блюда не переданы", thrown.getMessage());
    }

    @Test
    void testMealCreateFoodNotFound() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Mockito.when(foodRepository.existsById(1)).thenReturn(false);
        Meal meal = new Meal(1, "name", 2, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_NOT_FOUND, thrown.getStatusCode());
        Assertions.assertEquals("Блюдо не найдено", thrown.getMessage());
    }

    @Test
    void testMealCreateFoodIdIsNull() {
        Food food = new Food(null, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Meal meal = new Meal(1, "name", 2, localDate, foods);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.create(meal);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("id блюда не передан", thrown.getMessage());
    }

    @Test
    void testMealCreateSuccess() {
        Food food = new Food(1, "test", 600, (short) 5, (short) 5, (short) 34);
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        LocalDateTime localDate = LocalDateTime.now();
        Mockito.when(userRepository.existsById(2)).thenReturn(true);
        Mockito.when(foodRepository.existsById(1)).thenReturn(true);
        Meal meal = new Meal(1, "name", 2, localDate, foods);

        Assertions.assertDoesNotThrow(() -> {
            mealService.create(meal);
        });
    }

    @Test
    void testFindMealsAndCaloriesByDateUserNotFound() {
        LocalDate localDate = LocalDate.now();
        Mockito.when(userRepository.existsById(1)).thenReturn(false);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.findMealsAndCaloriesByDate(1, localDate);
        });
        Assertions.assertEquals(HttpServletResponse.SC_NOT_FOUND, thrown.getStatusCode());
        Assertions.assertEquals("Пользователь не найден", thrown.getMessage());
    }

    @Test
    void testFindMealsAndCaloriesByDateUserIdNull() {
        LocalDate localDate = LocalDate.now();

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            mealService.findMealsAndCaloriesByDate(null, localDate);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("id не передан", thrown.getMessage());
    }

    @Test
    void testFindMealsAndCaloriesByDateSuccess() {
        LocalDate localDate = LocalDate.now();
        User user = new User();
        user.setId(1);
        Mockito.when(userRepository.existsById(1)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> {
            mealService.findMealsAndCaloriesByDate(1, localDate);
        });
    }

}
