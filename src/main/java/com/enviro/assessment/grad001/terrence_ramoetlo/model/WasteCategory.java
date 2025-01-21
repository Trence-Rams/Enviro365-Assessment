package com.enviro.assessment.grad001.terrence_ramoetlo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "waste_categories")
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Category name cannot be blank")
    private String name;
    @NotBlank(message = "Category description cannot be blank")
    private String description;

}