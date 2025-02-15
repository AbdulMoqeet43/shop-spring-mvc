package com.code.repository;

import com.code.model.User;
import com.code.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<User, Integer> {

    // Find a user by ID
    Optional<User> findById(Integer id);

    // Get all users
    List<User> findAll();

    // Update username by ID
    default void updateUsername(User user, String newUsername) {
        user.setUsername(newUsername);
        save(user);
    }

    // Update role by ID
    default void updateRole(User user, UserRoles newRole) {
        user.setRole(newRole);
        save(user);
    }

    // Delete user by ID
    void deleteById(Integer id);

    // Save a new admin user
    default User saveNewAdmin(User user) {
        user.setRole(UserRoles.ADMIN);
        return save(user);
    }
}