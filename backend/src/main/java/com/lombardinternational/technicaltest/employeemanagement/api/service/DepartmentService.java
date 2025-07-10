package com.lombardinternational.technicaltest.employeemanagement.api.service;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.DepartmentCreationDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.DepartmentDto;
import com.lombardinternational.technicaltest.employeemanagement.api.mapper.DepartmentMapper;
import com.lombardinternational.technicaltest.employeemanagement.spi.model.Department;
import com.lombardinternational.technicaltest.employeemanagement.spi.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        return departmentMapper.toDtoList(departmentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec l'id: " + id));
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentCreationDto departmentDto) {
        // Vérifier si un département avec le même nom existe déjà
        if (departmentRepository.findByName(departmentDto.getName()).isPresent()) {
            throw new IllegalArgumentException("Un département avec ce nom existe déjà");
        }

        Department department = departmentMapper.toEntity(departmentDto);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentCreationDto departmentDto) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec l'id: " + id));

        // Vérifier si un autre département avec le même nom existe déjà
        departmentRepository.findByName(departmentDto.getName())
                .ifPresent(dep -> {
                    if (!dep.getId().equals(id)) {
                        throw new IllegalArgumentException("Un autre département avec ce nom existe déjà");
                    }
                });

        existingDepartment.setName(departmentDto.getName());
        existingDepartment = departmentRepository.save(existingDepartment);
        return departmentMapper.toDto(existingDepartment);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Département non trouvé avec l'id: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
