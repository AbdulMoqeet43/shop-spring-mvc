package com.code.controller;

import com.code.model.User;
import com.code.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("action") String action,
                                   HttpSession session) {

        if ("signin".equalsIgnoreCase(action)) {
            User user = userService.loginUser(username, password);

            if (user == null) {
                return ResponseEntity.badRequest().body("Invalid username or password");
            }

            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            String redirectUrl;
            switch (user.getRole()) {
                case CUSTOMER:
                    redirectUrl = "/home";
                    break;
                case ADMIN:
                    redirectUrl = "/admin";
                    break;
                case WAREHOUSE_STAFF:
                    redirectUrl = "/warehouse";
                    break;
                default:
                    redirectUrl = "/unauthorized";
                    break;
            }

            return ResponseEntity.ok().body("Redirecting to: " + redirectUrl);
        }

        else if ("signup".equalsIgnoreCase(action)) {
            return ResponseEntity.ok().body("Redirecting to: /register");
        }

        else {
            return ResponseEntity.badRequest().body("Invalid action");
        }
    }
}
