package com.code.controller;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.model.dto.UserInfoDTO;
import com.code.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String showAdminPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.ADMIN) {
            return "redirect:/unauthorized";
        }

        try {
            List<UserInfoDTO> users = adminService.getAllUsers();
            model.addAttribute("users", users);
            return "admin";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error?error=Failed to load admin page";
        }
    }

    @PostMapping("/update-username")
    public String updateUsername(@RequestParam Integer userId, @RequestParam String newUsername, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.ADMIN) {
            return "redirect:/unauthorized";
        }

        if (userId == null || newUsername.trim().isEmpty()) {
            return "redirect:/error?error=Invalid input";
        }

        try {
            adminService.updateUserName(userId, newUsername);
            return "redirect:/admin";
        } catch (Exception e) {
            return "redirect:/error?error=" + e.getMessage();
        }
    }

    @PostMapping("/update-role")
    public String updateRole(@RequestParam Integer userId, @RequestParam String role, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.ADMIN) {
            return "redirect:/unauthorized";
        }

        try {
            adminService.updateUserRole(userId, role);
            return "redirect:/admin";
        } catch (Exception e) {
            return "redirect:/error?error=" + e.getMessage();
        }
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Integer userId, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.ADMIN) {
            return "redirect:/unauthorized";
        }

        try {
            adminService.deleteUser(userId);
            return "redirect:/admin";
        } catch (Exception e) {
            return "redirect:/error?error=" + e.getMessage();
        }
    }

    @GetMapping("/add-admin")
    public String addAdminPage(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.ADMIN) {
            return "redirect:/unauthorized";
        }

        return "addNewAdmin";
    }

    @PostMapping("/submit-admin")
    public String submitAdmin(@RequestParam String newUsername, @RequestParam String password, @RequestParam String role, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.ADMIN) {
            return "redirect:/unauthorized";
        }

        if (newUsername == null || password == null || role == null) {
            return "redirect:/error?error=Invalid input";
        }

        try {
            adminService.addNewAdmin(newUsername, password);
            return "redirect:/admin";
        } catch (Exception e) {
            return "redirect:/error?error=" + e.getMessage();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/welcome";
    }
}
