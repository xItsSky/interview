package com.lombardinternational.technicaltest.employeemanagement.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBasicDto {
    private Long id;
    private String firstName;
    private String lastName;

    // Un getter pratique pour obtenir le nom complet
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
