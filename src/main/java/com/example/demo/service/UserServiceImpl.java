package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        log.info("New user: " + userRepository.save(user));
        return user;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean deleteUser(String email) {
        userRepository.deleteByEmail(email);
        return true;
    }

    public boolean clearAllUsers() {
        userRepository.deleteAll();
        return false;
    }

    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }
}

