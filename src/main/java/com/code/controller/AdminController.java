package com.code.controller;

import com.code.model.dto.UserInfoDTO;
import com.code.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String showAdminDashboard(Model model) {
        List<UserInfoDTO> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin"; // Corresponds to `admin.jsp`
    }

    @PostMapping("/updateUsername")
    public String updateUsername(@RequestParam Integer userId, @RequestParam String newUsername) {
        adminService.updateUsername(userId, newUsername);
        return "redirect:/admin";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestParam Integer userId, @RequestParam String role) {
        adminService.updateUserRole(userId, role);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer userId) {
        adminService.deleteUser(userId);
        return "redirect:/admin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin() {
        adminService.addNewAdmin();
        return "redirect:/admin";
    }
}
