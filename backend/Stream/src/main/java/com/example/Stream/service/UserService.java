package com.example.Stream.service;

import com.example.Stream.model.User;
import com.example.Stream.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user without hashing the password.
     * WARNING: This is insecure and not recommended for production.
     */
    public Optional<User> registerUser(User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Optional.empty(); // Email is already registered
        }

        // ❌ DO NOT HASH - Save password as plain text (⚠️ Not Secure)
        User savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }

    /**
     * Logs in a user by checking plain text password.
     */
    public Optional<User> loginUser(String email, String plainPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // ✅ Directly compare plain-text password
            if (user.getPassword().equals(plainPassword)) {
                return Optional.of(user); // ✅ Successful login
            } else {
                System.out.println("❌ Incorrect password!"); // Debugging log
            }
        } else {
            System.out.println("❌ No user found with this email!"); // Debugging log
        }

        return Optional.empty(); // ❌ Login failed
    }

    /**
     * Finds a user by ID.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Updates a user's information.
     */
    public Optional<User> updateUserById(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(updatedUser.getEmail());
            user.setUsername(updatedUser.getUsername());

            // ❌ Save password directly as plain text
            if (!updatedUser.getPassword().isEmpty()) {
                user.setPassword(updatedUser.getPassword());
            }

            return userRepository.save(user);
        });
    }

    /**
     * Deletes a user by ID.
     */
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
