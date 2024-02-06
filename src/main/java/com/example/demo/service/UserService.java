package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.List;


@Service
public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);

    User findUserByEmail(String email);

    boolean deleteUser(String email);

    boolean clearAllUsers();

    User getUser(String email);
}
