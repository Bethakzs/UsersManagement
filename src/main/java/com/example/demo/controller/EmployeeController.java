package com.example.demo.controller;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@CrossOrigin
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeByName(name);
        if(employeeDTO == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(Employee.builder()
                .name(employeeDTO.getName())
                .age(employeeDTO.getAge())
                .build());
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employeeService.createEmployee(employee);
    }
}
