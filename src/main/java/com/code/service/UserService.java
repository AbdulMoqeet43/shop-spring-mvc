package com.code.service;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepo userRepository;

    /**
     * Check if a user exists with the given credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if user exists, false otherwise
     */
    public boolean doesUserExist(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password); // Direct password comparison
    }

    /**
     * Authenticate a user based on username and password.
     *
     * @param username the username
     * @param password the password
     * @return User object if authentication is successful, otherwise null
     */
    public User loginUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password)) // Direct password check
                .map(user -> {
                    user.setRole(getUserRole(user.getId()));
                    return user;
                })
                .orElse(null);
    }

    /**
     * Register a new user.
     *
     * @param username the username
     * @param password the password
     * @param role     the user role
     */
    public void addNewAccount(String username, String password, UserRoles role) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // No encoding, direct storage
        user.setRole(role);

        userRepository.save(user);
    }

    /**
     * Get the user ID by username.
     *
     * @param username the username
     * @return the user ID
     */
    public int getUserId(String username) {
        if (username.isEmpty()) {
            return 0;
        }

        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElse(0);
    }

    /**
     * Fetch the user's role from the database.
     *
     * @param userId the user ID
     * @return the UserRoles enum
     */
    public UserRoles getUserRole(int userId) {
        return userRepository.findById(userId)
                .map(User::getRole)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
