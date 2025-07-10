package com.lombardinternational.technicaltest.employeemanagement.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String gender;
    private String email;

    // Référence simplifiée au département (juste l'ID et le nom)
    private DepartmentDto department;

    // Référence simplifiée au manager (juste l'ID et le nom complet)
    private EmployeeBasicDto manager;

    // Liste des subordonnés simplifiée
    private List<EmployeeBasicDto> subordinates;
}
