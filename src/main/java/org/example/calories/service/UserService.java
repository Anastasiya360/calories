package org.example.calories.service;


import org.example.calories.entity.User;
import org.springframework.stereotype.Service;



@Service
public interface UserService {

    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    User create(User user);

}
