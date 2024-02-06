package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "employee")
@Builder
public class Employee {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String  name;
    private Integer age;

}
