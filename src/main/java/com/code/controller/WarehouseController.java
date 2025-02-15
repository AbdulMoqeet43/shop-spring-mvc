package com.code.controller;

import com.code.model.User;
import com.code.model.UserRoles;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WarehouseController {

    // GET request to handle warehouse dashboard
    @GetMapping("/WarehouseServlet")
    public String warehouseDashboard(
            @SessionAttribute(name = "user", required = false) User user,
            RedirectAttributes redirectAttributes) {

        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            redirectAttributes.addFlashAttribute("error", "Unauthorized access!");
            return "redirect:/unauthorized.jsp";
        }

        return "redirect:/Warehouse.jsp";
    }

    // POST request to handle warehouse actions
    @PostMapping("/WarehouseServlet")
    public String handleWarehouseActions(
            @SessionAttribute(name = "user", required = false) User user,
            @RequestParam(name = "action", required = false) String action,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (user == null || action == null || action.trim().isEmpty()
                || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            redirectAttributes.addFlashAttribute("error", "Unauthorized action!");
            return "redirect:/unauthorized.jsp";
        }

        try {
            switch (action) {
                case "manageInventory":
                    return "redirect:/manageInventoryServlet";

                case "OrderProcessing":
                    return "redirect:/OrderProcessingServlet";

                case "Logout":
                    session.invalidate();
                    return "redirect:/welcome.jsp";

                default:
                    return "redirect:/welcome.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.jsp?error=An error occurred while processing your request.";
        }
    }
}
