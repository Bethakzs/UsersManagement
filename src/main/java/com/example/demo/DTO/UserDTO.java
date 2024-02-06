package com.example.demo.DTO;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserDTO {
    String username;
    String email;
    String password;
    int age;
}
