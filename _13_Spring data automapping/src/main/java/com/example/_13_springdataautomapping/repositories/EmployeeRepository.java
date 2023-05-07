package com.example._13_springdataautomapping.repositories;

import com.example._13_springdataautomapping.entities.Employee;
import com.example._13_springdataautomapping.entities.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<EmployeeDto> findByBirthdayBeforeOrderBySalaryDesc(LocalDate beforeYear);
}
