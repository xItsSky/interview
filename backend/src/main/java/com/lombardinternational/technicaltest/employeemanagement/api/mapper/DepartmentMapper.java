package com.lombardinternational.technicaltest.employeemanagement.api.mapper;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.DepartmentCreationDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.DepartmentDto;
import com.lombardinternational.technicaltest.employeemanagement.spi.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {EmployeeBasicMapper.class})
public interface DepartmentMapper {

    // Conversion complète de l'entité vers le DTO
    @Mapping(target = "employees", source = "employees", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    DepartmentDto toDto(Department department);

    // Conversion du DTO vers l'entité
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentDto departmentDto);

    // Conversion du DTO de création vers l'entité
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentCreationDto creationDto);

    // Conversion d'une liste
    List<DepartmentDto> toDtoList(List<Department> departments);
}

