package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import java.util.List;

@CrossOrigin
@Tag(name = "main_methods")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create user",
            description = "Create a new user in the database. The user must have a username, email, password, and age.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User object that needs to be added to the database",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid user data"),
                    @ApiResponse(responseCode = "409", description = "User already exists"),
                    @ApiResponse(responseCode = "500", description = "User not created due to server error")
            }
    )
    @PostMapping("/api/create-user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User existingUser = userService.findUserByEmail(userDTO.getEmail());
        if (existingUser != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        if (userDTO.getUsername() == null || userDTO.getEmail() == null || userDTO.getPassword() == null || userDTO.getAge() == 0
                || userDTO.getUsername().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            return new ResponseEntity<>("Invalid user data", HttpStatus.BAD_REQUEST);
        }
        User createdUser = userService.createUser(User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .build());
        if (createdUser == null) {
            return new ResponseEntity<>("User not created", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userDTO.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/api/all-users")
    @Operation(
            summary = "Get all users",
            description = "Retrieve all users from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Users not found")
            }
    )
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/user/{email}")
    @Operation(
            summary = "Get user by email",
            description = "Retrieve a user from the database by their email.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = userService.getUser(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/api/remove-user/{email}")
    @Operation(
            summary = "Remove user by email",
            description = "Remove a user from the database by their email.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User removed successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<String> removeUser(@PathVariable String email) {
        if (userService.deleteUser(email)) {
            return new ResponseEntity<>("User was deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/remove-all-users")
    @Operation(
            summary = "Remove all users",
            description = "Remove all users from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All users removed successfully"),
                    @ApiResponse(responseCode = "404", description = "Users not found")
            }
    )
    public ResponseEntity<String> removeAllUsers() {
        if (userService.clearAllUsers()) {
            return new ResponseEntity<>("Users were deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/edit-user")
    @Operation(
            summary = "Edit user",
            description = "Edit an existing user in the database. The user must have a username, email, password, and age.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User object that needs to be edited in the database",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User edited successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "User not edited due to server error")
            }
    )
    public ResponseEntity<User> editUser(@RequestBody UserDTO userDTO) {
        if(userService.findUserByEmail(userDTO.getEmail()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.createUser(User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .build()), HttpStatus.OK);
    }
}