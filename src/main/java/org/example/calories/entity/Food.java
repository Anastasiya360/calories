package org.example.calories.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "calories", name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount_of_calories")
    private Integer amountOfCalories;

    @Column(name = "proteins")
    private Short proteins;

    @Column(name = "fats")
    private Short fats;

    @Column(name = "carbohydrates")
    private Short carbohydrates;

}
