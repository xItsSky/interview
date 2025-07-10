package com.lombardinternational.technicaltest.employeemanagement.api.mapper;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeBasicDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeCreationDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeDto;
import com.lombardinternational.technicaltest.employeemanagement.spi.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {DepartmentMapper.class})
public interface EmployeeMapper {

    // Conversion de l'entité vers le DTO complet
    EmployeeDto toDto(Employee employee);

    // Conversion du DTO vers l'entité
    @Mapping(target = "subordinates", ignore = true) // Géré manuellement pour éviter les cycles
    Employee toEntity(EmployeeDto employeeDto);

    // Conversion d'une liste d'employés vers DTOs complets
    List<EmployeeDto> toDtoList(List<Employee> employees);

    // Conversion du DTO de création vers l'entité
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "subordinates", ignore = true)
    Employee toEntity(EmployeeCreationDto creationDto);
}
