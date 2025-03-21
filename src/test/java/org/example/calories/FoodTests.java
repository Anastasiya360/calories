package org.example.calories;


import jakarta.servlet.http.HttpServletResponse;
import org.example.calories.entity.Food;
import org.example.calories.exception.ApiException;
import org.example.calories.service.FoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodTests {

    @Autowired
    private FoodService foodService;

    @Test
    void testFoodCreateNameNull() {
        Food food = new Food(1, null, 600, (short) 5, (short) 5, (short) 34);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            foodService.create(food);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Имя не передано", thrown.getMessage());
    }

    @Test
    void testFoodCreateNameIsBlank() {
        Food food = new Food(1, " ", 600, (short) 5, (short) 5, (short) 34);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            foodService.create(food);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Имя не передано", thrown.getMessage());
    }

    @Test
    void testFoodCreateAmountOfCaloriesNull() {
        Food food = new Food(1, "name", null, (short) 5, (short) 5, (short) 34);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            foodService.create(food);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Количество калорий не передано", thrown.getMessage());
    }

    @Test
    void testFoodCreateProteinsNull() {
        Food food = new Food(1, "name", 600, null, (short) 5, (short) 34);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            foodService.create(food);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Количество белка не указано", thrown.getMessage());
    }

    @Test
    void testFoodCreateFatsNull() {
        Food food = new Food(1, "name", 600, (short) 5, null, (short) 34);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            foodService.create(food);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Количество жиров не указано", thrown.getMessage());
    }

    @Test
    void testFoodCreateCarbohydratesNull() {
        Food food = new Food(1, "name", 600, (short) 5, (short) 5, null);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            foodService.create(food);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Количество углеводов не указано", thrown.getMessage());
    }

    @Test
    void testFoodCreateSuccess() {
        Food food = new Food(1, "name", 600, (short) 5, (short) 5, (short) 36);

        Assertions.assertDoesNotThrow(() -> {
            foodService.create(food);
        });
    }
}
