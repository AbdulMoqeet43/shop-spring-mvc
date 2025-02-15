package com.code.controller;

import com.code.model.ShoppingCartInfo;
import com.code.service.ShoppingCartService;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;

    @GetMapping("/shopping-cart")
    public String getShoppingCart(@SessionAttribute(name = "username", required = false) String username, Model model) {
        if (username == null) {
            model.addAttribute("error", "User not logged in.");
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }

        try {
            int userId = userService.getUserId(username);
            List<ShoppingCartInfo> shoppingCart = shoppingCartService.fetchAllItemsInShoppingCart(userId);
            model.addAttribute("shoppingCart", shoppingCart);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching shopping cart.");
        }

        return "shoppingCart"; // Forward to shoppingCart.jsp
    }
}
