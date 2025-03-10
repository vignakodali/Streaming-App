package com.example.Stream.controller;

import com.example.Stream.model.User;
import com.example.Stream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        Optional<User> registeredUser = userService.registerUser(user);
        return registeredUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build()); // Return empty 400 response
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        Optional<User> loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        return loggedInUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).build()); // Return empty 401 response
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build()); // Return 404 with empty body
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUserById(id, user);
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build()); // Return empty 404 response
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        return deleted ? ResponseEntity.ok().build()
                : ResponseEntity.status(404).build(); // Return 404 with empty body
    }
}
