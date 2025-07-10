package com.lombardinternational.technicaltest.employeemanagement.spi.repository;

import com.lombardinternational.technicaltest.employeemanagement.spi.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
