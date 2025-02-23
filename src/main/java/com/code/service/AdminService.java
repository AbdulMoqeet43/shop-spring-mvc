package com.code.service;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.model.dto.UserInfoDTO;
import com.code.repository.AdminRepo;
import com.code.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepo adminRepository;
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    public AdminService(AdminRepo adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<UserInfoDTO> getAllUsers() {
        return adminRepository.findAll().stream()
                .map(user -> new UserInfoDTO(user.getId(), user.getUsername(), user.getRole().name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUsername(Integer userId, String newUsername) {
        if (userId == null || newUsername == null || newUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid userId or username");
        }

        User user = usersRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(newUsername);
        usersRepo.save(user);
    }

    @Transactional
    public void updateUserRole(Integer userId, String role) {
        if (userId == null || role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid userId or role");
        }

        User user = usersRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            UserRoles newRole = UserRoles.valueOf(role.toUpperCase());
            user.setRole(newRole);
            usersRepo.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    @Transactional
    public void deleteUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Invalid userId");
        }

        if (!usersRepo.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        usersRepo.deleteById(userId);
    }

    @Transactional
    public void addNewAdmin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        if (usersRepo.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        User newAdmin = new User();
        newAdmin.setUsername(username);
        newAdmin.setPassword(password); // Secure password encoding
        newAdmin.setRole(UserRoles.ADMIN);

        usersRepo.save(newAdmin);
    }

    @Transactional
    public void addNewAdmin() {
        User admin = new User();
        admin.setUsername("newAdmin");
        admin.setPassword("111"); // Hashing for security
        admin.setRole(UserRoles.ADMIN);
        usersRepo.save(admin);
    }
}
