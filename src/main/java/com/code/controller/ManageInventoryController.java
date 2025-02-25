package com.code.controller;

import com.code.model.ItemInfo;
import com.code.service.ManageInventoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manageInventory")
public class ManageInventoryController {

    @Autowired
    private ManageInventoryService manageInventoryService;

    @GetMapping
    public String showInventory(Model model, HttpSession session) {
        List<ItemInfo> inventory = manageInventoryService.fetchAllInventory();
        model.addAttribute("inventory", inventory);
        return "manageInventory";  // Maps to `inventory.jsp`
    }

    @PostMapping("/increase")
    public String increaseItem(@RequestParam int itemID, @RequestParam int quantity, Model model, HttpSession session) {
        manageInventoryService.increaseItem(itemID, quantity);
        return "redirect:/manageInventory";  // Redirect to inventory.jsp
    }

    @PostMapping("/decrease")
    public String decreaseItem(@RequestParam int itemID, @RequestParam int quantity, Model model, HttpSession session) {
        manageInventoryService.decreaseItem(itemID, quantity);
        return "redirect:/manageInventory";
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String itemName, @RequestParam String description,
                          @RequestParam double price, @RequestParam int quantity) {
        manageInventoryService.addItem(itemName, description, price, quantity);
        return "redirect:/manageInventory";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam int itemID) {
        manageInventoryService.deleteItem(itemID);
        return "redirect:/manageInventory";
    }
}
