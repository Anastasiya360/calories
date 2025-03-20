package org.example.calories.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.example.calories.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Interaction with users")
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface UserController {

    @Operation(
            summary = "Create user"
    )
    @PostMapping(path = "/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Имя не передано
                            * Email не передан
                            * Возраст не указан
                            * Вес не указан
                            * Рост не указан
                            * Цель задана неверно
                            """)
    })
    User create(@RequestBody User user);
}
