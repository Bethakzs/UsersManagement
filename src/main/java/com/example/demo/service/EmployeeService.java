package com.example.demo.service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeDTO findEmployeeByName(String name);

    List<Employee> getAllEmployees();

    void createEmployee(Employee employee);
}
