package com.lombardinternational.technicaltest.employeemanagement.api.mapper;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeBasicDto;
import com.lombardinternational.technicaltest.employeemanagement.spi.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeBasicMapper {

    // Conversion simplifiée d'un employé vers DTO basique
    EmployeeBasicDto toBasicDto(Employee employee);

    // Conversion d'une liste d'employés vers liste de DTOs basiques
    List<EmployeeBasicDto> toBasicDtoList(List<Employee> employees);

    // Conversion d'un DTO basique vers un employé (utilisée par DepartmentMapper)
    default Employee fromBasicDto(EmployeeBasicDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());

        return employee;
    }
}
