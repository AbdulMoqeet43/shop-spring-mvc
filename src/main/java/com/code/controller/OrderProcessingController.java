package com.code.controller;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.model.OrderInfo;
import com.code.service.ManageOrdersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderProcessingController {

    @Autowired
    private ManageOrdersService manageOrdersService;

    @GetMapping
    public String fetchAllOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";  // Return error view
        }

        try {
            List<OrderInfo> allOrdersInfo = manageOrdersService.fetchAllOrders();
            model.addAttribute("orders", allOrdersInfo);
            return "orders";  // Return orders view
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load orders");
            return "error";
        }
    }

    @PostMapping
    public String processOrder(@RequestParam("action") String action, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || action == null || action.isEmpty() || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        switch (action) {
            case "EditOrder":
                model.addAttribute("message", "Edit order functionality not implemented yet");
                return "edit-order";  // Return the edit-order view
            default:
                model.addAttribute("error", "Invalid action");
                return "error";
        }
    }
}
