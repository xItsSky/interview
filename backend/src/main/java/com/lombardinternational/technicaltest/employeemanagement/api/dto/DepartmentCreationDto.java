package com.lombardinternational.technicaltest.employeemanagement.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilisé pour la création d'un nouveau département
 * Ne contient que les informations nécessaires à la création
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreationDto {

    @NotBlank(message = "Le nom du département ne peut pas être vide")
    @Size(max = 100, message = "Le nom du département ne peut pas dépasser 100 caractères")
    private String name;
}
