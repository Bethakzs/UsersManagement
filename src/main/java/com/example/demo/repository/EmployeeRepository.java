package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, UUID> {
    Employee findByName(String name);
}
