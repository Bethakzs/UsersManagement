package com.example.demo.service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeDTO findEmployeeByName(String name) {
        Employee employee = employeeRepository.findByName(name);
        return EmployeeDTO.builder()
                .name(employee.getName())
                .age(employee.getAge())
                .build();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
