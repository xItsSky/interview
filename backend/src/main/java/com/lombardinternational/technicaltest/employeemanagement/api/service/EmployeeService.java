package com.lombardinternational.technicaltest.employeemanagement.api.service;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeBasicDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeCreationDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeDto;
import com.lombardinternational.technicaltest.employeemanagement.api.mapper.EmployeeBasicMapper;
import com.lombardinternational.technicaltest.employeemanagement.api.mapper.EmployeeMapper;
import com.lombardinternational.technicaltest.employeemanagement.spi.model.Department;
import com.lombardinternational.technicaltest.employeemanagement.spi.model.Employee;
import com.lombardinternational.technicaltest.employeemanagement.spi.repository.DepartmentRepository;
import com.lombardinternational.technicaltest.employeemanagement.spi.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeBasicMapper employeeBasicMapper;

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Employé non trouvé avec l'id: " + id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicDto> getEmployeesByManagerId(Long managerId) {
        return employeeBasicMapper.toBasicDtoList(employeeRepository.findByManagerId(managerId));
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicDto> getEmployeesByDepartmentId(Long departmentId) {
        return employeeBasicMapper.toBasicDtoList(employeeRepository.findByDepartmentId(departmentId));
    }

    @Transactional(readOnly = true)
    public List<EmployeeBasicDto> getTopLevelEmployees() {
        return employeeBasicMapper.toBasicDtoList(employeeRepository.findByManagerIsNull());
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto) {
        // Vérifier si l'email est déjà utilisé
        if (employeeRepository.findByEmail(employeeCreationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cet email est déjà utilisé");
        }

        Employee employee = employeeMapper.toEntity(employeeCreationDto);

        // Associer le département si spécifié
        if (employeeCreationDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeCreationDto.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec l'id: " + employeeCreationDto.getDepartmentId()));
            employee.setDepartment(department);
        }

        // Associer le manager si spécifié
        if (employeeCreationDto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeCreationDto.getManagerId())
                    .orElseThrow(() -> new EntityNotFoundException("Manager non trouvé avec l'id: " + employeeCreationDto.getManagerId()));
            employee.setManager(manager);
        }

        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeCreationDto employeeCreationDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé non trouvé avec l'id: " + id));

        // Vérifier si l'email est déjà utilisé par un autre employé
        employeeRepository.findByEmail(employeeCreationDto.getEmail()).ifPresent(existingEmployeeByEmail -> {
            if (!existingEmployeeByEmail.getId().equals(id)) {
                throw new IllegalArgumentException("Cet email est déjà utilisé par un autre employé");
            }
        });

        // Mise à jour des informations de base
        existingEmployee.setFirstName(employeeCreationDto.getFirstName());
        existingEmployee.setLastName(employeeCreationDto.getLastName());
        existingEmployee.setJobTitle(employeeCreationDto.getJobTitle());
        existingEmployee.setContractStartDate(employeeCreationDto.getContractStartDate());
        existingEmployee.setContractEndDate(employeeCreationDto.getContractEndDate());
        existingEmployee.setGender(employeeCreationDto.getGender());
        existingEmployee.setEmail(employeeCreationDto.getEmail());

        // Mise à jour du département
        if (employeeCreationDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeCreationDto.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Département non trouvé avec l'id: " + employeeCreationDto.getDepartmentId()));
            existingEmployee.setDepartment(department);
        } else {
            existingEmployee.setDepartment(null);
        }

        // Mise à jour du manager (vérifier qu'un employé ne se désigne pas lui-même comme manager)
        if (employeeCreationDto.getManagerId() != null) {
            if (employeeCreationDto.getManagerId().equals(id)) {
                throw new IllegalArgumentException("Un employé ne peut pas être son propre manager");
            }
            Employee manager = employeeRepository.findById(employeeCreationDto.getManagerId())
                    .orElseThrow(() -> new EntityNotFoundException("Manager non trouvé avec l'id: " + employeeCreationDto.getManagerId()));
            existingEmployee.setManager(manager);
        } else {
            existingEmployee.setManager(null);
        }

        existingEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toDto(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employé non trouvé avec l'id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
