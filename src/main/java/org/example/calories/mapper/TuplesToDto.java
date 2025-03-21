package org.example.calories.mapper;


import jakarta.persistence.Tuple;
import org.example.calories.dto.MealCalorieDto;

public class TuplesToDto {

    public static MealCalorieDto tupleToDto(Tuple tuple) {
        return MealCalorieDto.builder()
                .meals(tuple.get(0, Long.class))
                .calories(tuple.get(1, Long.class))
                .build();
    }
}
