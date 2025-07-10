package com.lombardinternational.technicaltest.employeemanagement.api.dto;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeBasicDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;

    // Liste des employés dans ce département (version basique pour éviter les références circulaires)
    private List<EmployeeBasicDto> employees;
}
