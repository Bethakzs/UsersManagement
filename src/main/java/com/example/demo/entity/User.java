package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private int age;

    public String toString() {
        return "id = " + id + " | username = " + username + " | email = " + email + " | age = " + age;
    }
}
