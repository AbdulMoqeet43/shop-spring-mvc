package com.code.controller;

import com.code.model.User;
import com.code.model.UserRoles;
import com.code.model.ItemInfo;
import com.code.service.ManageInventoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class ManageInventoryController {

    @Autowired
    private ManageInventoryService manageInventoryService;

    @GetMapping
    public String fetchInventory(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        try {
            List<ItemInfo> allItemsInfo = manageInventoryService.fetchAllInventory();
            model.addAttribute("items", allItemsInfo);
            return "inventory";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load inventory");
            return "error";
        }
    }

    @PostMapping("/increase")
    public String increaseItem(@RequestParam int itemID, @RequestParam int quantity, HttpSession session, Model model) {
        return processInventoryChange(itemID, quantity, session, "increase", model);
    }

    @PostMapping("/decrease")
    public String decreaseItem(@RequestParam int itemID, @RequestParam int quantity, HttpSession session, Model model) {
        return processInventoryChange(itemID, quantity, session, "decrease", model);
    }

    @DeleteMapping("/{itemID}")
    public String deleteItem(@PathVariable int itemID, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        try {
            manageInventoryService.deleteItem(itemID);
            model.addAttribute("message", "Item deleted successfully");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting item");
            return "error";
        }
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String itemName, @RequestParam String description,
                          @RequestParam double price, @RequestParam int quantity, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        try {
            manageInventoryService.addItem(itemName, description, price, quantity);
            model.addAttribute("message", "Item added successfully");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "Error adding item");
            return "error";
        }
    }

    @PutMapping("/update")
    public String updateItem(@RequestParam int itemID, @RequestParam String name,
                             @RequestParam String description, @RequestParam double price,
                             @RequestParam int quantity, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        try {
            manageInventoryService.updateItem(itemID, name, description, price, quantity);
            model.addAttribute("message", "Item updated successfully");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating item");
            return "error";
        }
    }

    @PostMapping("/category")
    public String addCategory(@RequestParam String categoryName, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        try {
            manageInventoryService.addCategory(categoryName);
            model.addAttribute("message", "Category added successfully");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "Error adding category");
            return "error";
        }
    }

    private String processInventoryChange(int itemID, int quantity, HttpSession session, String action, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != UserRoles.WAREHOUSE_STAFF) {
            model.addAttribute("error", "Unauthorized access");
            return "error";
        }

        try {
            if (action.equals("increase")) {
                manageInventoryService.increaseItem(itemID, quantity);
            } else {
                manageInventoryService.decreaseItem(itemID, quantity);
            }
            model.addAttribute("message", action + " successful");
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "Error processing inventory change");
            return "error";
        }
    }
}
