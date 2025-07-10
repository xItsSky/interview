package com.lombardinternational.technicaltest.employeemanagement.api.controller;

import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeBasicDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeCreationDto;
import com.lombardinternational.technicaltest.employeemanagement.api.dto.EmployeeDto;
import com.lombardinternational.technicaltest.employeemanagement.api.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/by-manager/{managerId}")
    public ResponseEntity<List<EmployeeBasicDto>> getEmployeesByManager(@PathVariable Long managerId) {
        return ResponseEntity.ok(employeeService.getEmployeesByManagerId(managerId));
    }

    @GetMapping("/by-department/{departmentId}")
    public ResponseEntity<List<EmployeeBasicDto>> getEmployeesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentId(departmentId));
    }

    @GetMapping("/top-level")
    public ResponseEntity<List<EmployeeBasicDto>> getTopLevelEmployees() {
        return ResponseEntity.ok(employeeService.getTopLevelEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeCreationDto employeeCreationDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeCreationDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeCreationDto employeeCreationDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeCreationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
