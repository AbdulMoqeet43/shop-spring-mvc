package com.code.repository;

import com.code.model.User;
import com.code.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {

    // Fetch user by username and password (Consider using hashed password comparison)
    Optional<User> findByUsernameAndPassword(String username, String password);

    // Fetch user by username
    Optional<User> findByUsername(String username);

    // Fetch role based on user ID
    @Query("SELECT u.role FROM User u WHERE u.id = :userId")
    Optional<UserRoles> getUserRoleById(int userId);

    boolean existsByUsername(String username);
}
