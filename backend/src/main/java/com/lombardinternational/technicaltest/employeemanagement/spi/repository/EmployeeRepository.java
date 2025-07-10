package com.lombardinternational.technicaltest.employeemanagement.spi.repository;


import com.lombardinternational.technicaltest.employeemanagement.spi.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    @EntityGraph(attributePaths = {"department", "manager"})
    List<Employee> findAll();

    List<Employee> findByManagerId(Long managerId);
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByManagerIsNull();

    Optional<Employee> findByEmail(String email);

}
