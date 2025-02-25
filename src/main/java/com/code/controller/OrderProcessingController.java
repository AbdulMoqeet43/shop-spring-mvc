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
@RequestMapping("/orderProcessing")
public class OrderProcessingController {

    @Autowired
    private ManageOrdersService manageOrdersService;

    @GetMapping
    public String fetchAllOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            return "redirect:/unauthorized";
        }

        try {
            List<OrderInfo> allOrdersInfo = manageOrdersService.fetchAllOrders();
            model.addAttribute("orders", allOrdersInfo);
            return "orderProcessing"; // JSP view name
        } catch (Exception e) {
            return "redirect:/error?message=Failed to load orders";
        }
    }

    @PostMapping
    public String processOrder(@RequestParam("action") String action,
                               @RequestParam(value = "orderID", required = false) Long orderID,
                               HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || action == null || action.isEmpty() || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            return "redirect:/unauthorized";
        }

        switch (action) {
            case "Edit":
                if (orderID != null) {
                    model.addAttribute("message", "Edit order functionality not implemented yet");
                    return "editOrder"; // Return the editOrder view
                }
                return "redirect:/error?message=Invalid order ID";
            default:
                return "redirect:/error?message=Invalid action";
        }
    }
}