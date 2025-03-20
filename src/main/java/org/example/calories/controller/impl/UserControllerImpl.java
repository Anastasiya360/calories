package org.example.calories.controller.impl;


import lombok.RequiredArgsConstructor;
import org.example.calories.controller.UserController;
import org.example.calories.entity.User;
import org.example.calories.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public User create(User user) {
        return userService.create(user);
    }
}
