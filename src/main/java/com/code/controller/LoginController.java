package com.code.controller;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String action,
                        HttpSession session,
                        Model model) {

        if ("signin".equalsIgnoreCase(action)) {
            User user = userService.loginUser(username, password);

            if (user == null) {
                model.addAttribute("error", "Invalid username or password");
                return "login"; // Redirect back to login page
            }

            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            // Redirect based on role
            switch (user.getRole()) {
                case CUSTOMER:
                    return "redirect:/home";
                case ADMIN:
                    return "redirect:/admin";
                case WAREHOUSE_STAFF:
                    return "redirect:/warehouse";
                default:
                    return "redirect:/unauthorized";
            }
        }

        if ("signup".equalsIgnoreCase(action)) {
            return "redirect:/register";
        }

        model.addAttribute("error", "Invalid action");
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        if (user.getRole() == null) {
            user.setRole(UserRoles.CUSTOMER); // Default role
        }
        userService.save(user);
        model.addAttribute("message", "Signup successful!");
        return "welcome";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate session
        request.getSession().invalidate();

        // Redirect to welcome page
        return "redirect:/";
    }


}
