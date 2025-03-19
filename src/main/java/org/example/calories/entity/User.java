package org.example.calories.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "calories", name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Short age;

    @Column(name = "weight")
    private Short weight;

    @Column(name = "height")
    private Short height;

    @Column(name = "goal")
    private String goal;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "calorie_norm")
    private Integer calorieNorm;
}
