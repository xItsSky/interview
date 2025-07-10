package com.lombardinternational.technicaltest.employeemanagement.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreationDto {
    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @NotBlank(message = "Le titre du poste est obligatoire")
    private String jobTitle;

    @NotNull(message = "La date de début de contrat est obligatoire")
    private LocalDate contractStartDate;

    private LocalDate contractEndDate;

    @NotBlank(message = "Le genre est obligatoire")
    private String gender;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    private Long departmentId;
    private Long managerId;
}
