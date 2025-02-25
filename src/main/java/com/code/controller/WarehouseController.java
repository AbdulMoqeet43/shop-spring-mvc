package com.code.controller;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.service.ShoppingCartService;
import com.code.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public WarehouseController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String warehouseDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            return "redirect:/unauthorized";
        }

        model.addAttribute("username", user.getUsername());
        return "Warehouse"; // Loads warehouse.jsp
    }

    @PostMapping
    public String handleWarehouseActions(@RequestParam("action") String action, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            return "redirect:/unauthorized";
        }

        switch (action) {
            case "manageInventory":
                return "redirect:/manageInventory";
            case "OrderProcessing":
                return "redirect:/orderProcessing";
            case "Logout":
                session.invalidate();
                return "redirect:/";
            default:
                return "redirect:/warehouse";
        }
    }
}
