package com.code.controller;

import com.code.model.UserRoles;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String registerUser(@RequestParam String chooseUsername,
                               @RequestParam String choosePassword,
                               @RequestParam(required = false) String action,
                               Model model) {

        if (chooseUsername.isEmpty() || choosePassword.isEmpty()) {
            model.addAttribute("error", "Missing credentials");
            return "welcome"; // Forward to welcome.jsp with error message
        }

        if ("signup".equals(action)) {
            if (userService.doesUserExist(chooseUsername, choosePassword)) {
                model.addAttribute("message", "User already exists!");
            } else {
                userService.addNewAccount(chooseUsername, choosePassword, UserRoles.CUSTOMER);
                model.addAttribute("message", "Register successful!");
            }
        }
        return "welcome"; // Forward to welcome.jsp
    }
}
