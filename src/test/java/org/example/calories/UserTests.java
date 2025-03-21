package org.example.calories;

import jakarta.servlet.http.HttpServletResponse;
import org.example.calories.entity.User;
import org.example.calories.exception.ApiException;
import org.example.calories.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;


    @Test
    void testUserCreateNameNull() {
        User user = new User(1, null, "email", (short) 24, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Имя не передано", thrown.getMessage());
    }

    @Test
    void testUserCreateNameIsBlank() {
        User user = new User(1, " ", "email", (short) 24, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Имя не передано", thrown.getMessage());
    }

    @Test
    void testUserCreateEmailNull() {
        User user = new User(1, "name", null, (short) 24, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Email не передан", thrown.getMessage());
    }

    @Test
    void testUserCreateEmailIsBlank() {
        User user = new User(1, "name", " ", (short) 24, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Email не передан", thrown.getMessage());
    }

    @Test
    void testUserCreateAgeNull() {
        User user = new User(1, "name", "email", null, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Возраст не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateAgeNotValid() {
        User user = new User(1, "name", "email", (short) -2, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Возраст не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateAgeNotValid2() {
        User user = new User(1, "name", "email", (short) 300, (short) 56, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Возраст не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateWeightNull() {
        User user = new User(1, "name", "email",(short) 24 , null, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Вес не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateWeightNotValid() {
        User user = new User(1, "name", "email", (short) 24, (short) -8, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Вес не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateWeightNotValid2() {
        User user = new User(1, "name", "email", (short) 24, (short) 560, (short) 170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Вес не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateHeightNull() {
        User user = new User(1, "name", "email",(short) 24 , (short) 64, null, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Рост не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateHeightNotValid() {
        User user = new User(1, "name", "email", (short) 24, (short) 64, (short) 1170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Рост не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateHeightNotValid2() {
        User user = new User(1, "name", "email", (short) 24, (short) 64, (short) -170, "GAIN", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Рост не указан или указан некорректно", thrown.getMessage());
    }

    @Test
    void testUserCreateGoalNotValid() {
        User user = new User(1, "name", "email", (short) 24, (short) 64, (short) 170, "test", 2000);

        ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
            userService.create(user);
        });
        Assertions.assertEquals(HttpServletResponse.SC_BAD_REQUEST, thrown.getStatusCode());
        Assertions.assertEquals("Цель задана неверно (варианты: WEIGHT_LOSS, MAINTENANCE, GAIN)", thrown.getMessage());
    }

    @Test
    void testUserCreateSuccess() {
        User user = new User(1, "name", "email", (short) 24, (short) 64, (short) 170, "GAIN", 2000);

        Assertions.assertDoesNotThrow(() -> {
            userService.create(user);
        });
    }
}
