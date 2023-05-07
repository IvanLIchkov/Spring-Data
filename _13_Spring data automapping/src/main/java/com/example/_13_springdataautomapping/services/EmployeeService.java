package com.example._13_springdataautomapping.services;

import com.example._13_springdataautomapping.entities.Employee;
import com.example._13_springdataautomapping.entities.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);

    void save(Employee employee);

    List<EmployeeDto> findEmployeesBornBefore(int year);

    List<Employee> findAll();
}
