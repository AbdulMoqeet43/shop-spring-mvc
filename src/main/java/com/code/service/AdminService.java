package com.code.service;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.model.dto.UserInfoDTO;
import com.code.repository.AdminRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepo adminRepository;

    public AdminService(AdminRepo adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<UserInfoDTO> getAllUsers() {
        return adminRepository.findAll().stream()
                .map(user -> new UserInfoDTO(user.getId(), user.getUsername(), user.getRole().name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUserName(Integer userId, String newUsername) {
        if (userId == null || newUsername == null || newUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid userId or username");
        }

        User user = adminRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setUsername(newUsername);
        adminRepository.save(user);
    }

    @Transactional
    public void updateUserRole(Integer userId, String newRole) {
        if (userId == null || newRole == null || newRole.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid userId or role");
        }

        User user = adminRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        try {
            user.setRole(UserRoles.valueOf(newRole.toUpperCase())); // Convert role to ENUM
            adminRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + newRole);
        }
    }

    @Transactional
    public void deleteUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Invalid userId");
        }

        adminRepository.deleteById(userId);
    }

    @Transactional
    public void addNewAdmin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User newAdmin = new User();
        newAdmin.setUsername(username);
        newAdmin.setPassword(password);
//        newAdmin.setPassword(passwordEncoder.encode(password)); // Secure password encoding
        newAdmin.setRole(UserRoles.ADMIN);

        adminRepository.save(newAdmin);
    }
}
