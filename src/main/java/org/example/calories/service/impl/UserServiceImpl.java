package org.example.calories.service.impl;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.example.calories.entity.User;
import org.example.calories.enums.Goal;
import org.example.calories.exception.ApiException;
import org.example.calories.repository.UserRepository;
import org.example.calories.service.UserService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        user.setId(null);
        checkUser(user);
        user.setCalorieNorm(calculateCalorieNorm(user));
        return userRepository.save(user);
    }

    @Override
    public void existsUser(Integer id) {
        if (id == null) {
            throw new ApiException("id не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (!userRepository.existsById(id)) {
            throw new ApiException("Пользователь не найден", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void checkUser(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new ApiException("Имя не передано", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ApiException("Email не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (user.getAge() == null || user.getAge() > 100 || user.getAge() < 0) {
            throw new ApiException("Возраст не указан или указан некорректно", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (user.getWeight() == null || user.getWeight() > 300 || user.getWeight() < 0) {
            throw new ApiException("Вес не указан или указан некорректно", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (user.getHeight() == null || user.getHeight() > 250 || user.getHeight() < 0) {
            throw new ApiException("Рост не указан или указан некорректно", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (!EnumUtils.isValidEnum(Goal.class, user.getGoal())) {
            throw new ApiException("Цель задана неверно (варианты: WEIGHT_LOSS, MAINTENANCE, GAIN)", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private int calculateCalorieNorm(User user) {
        return (int) (655.1 + (9.563 * user.getWeight()) + (1.85 * user.getHeight()) - (4.676 * user.getAge()));
    }
}
