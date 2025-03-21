package org.example.calories.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.example.calories.dto.MealCalorieDto;
import org.example.calories.dto.MealDataDto;
import org.example.calories.entity.Meal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meal")
@Tag(name = "Meal", description = "Interaction with meals")
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface MealController {

    @Operation(
            summary = "Create meal"
    )
    @PostMapping(path = "/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Meal.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Имя не передано
                            * Блюдо с id: " + id + " не найдено
                            """),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND,
                    description = """
                            * Пользователь не найден
                            """)
    })
    Meal create(@RequestBody Meal meal);

    @Operation(
            summary = "Get count meals and calories by date"
    )
    @PostMapping(path = "/get/meals-calories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealCalorieDto.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND,
                    description = """
                            * Пользователь не найден
                            """)
    })
    MealCalorieDto findMealsAndCaloriesByDate(@RequestParam Integer userId,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Operation(
            summary = "Checking whether the user has met the calorie norm"
    )
    @PostMapping(path = "/check/calorie-norm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Записей о приемах пищи за этот день нет
                            """),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND,
                    description = """
                            * Пользователь не найден
                            """)
    })
    Boolean checkCalorieNorm(@RequestParam Integer userId,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Operation(
            summary = "Get food history by day"
    )
    @PostMapping(path = "/get/meals-history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealCalorieDto.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND,
                    description = """
                            * Пользователь не найден
                            """)
    })
    List<MealDataDto> historyOfMeals(@RequestParam Integer userId);
}
