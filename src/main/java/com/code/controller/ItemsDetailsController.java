package com.code.controller;

import com.code.service.*;
import com.code.model.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/items")
public class ItemsDetailsController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{itemId}")
    public String getItemDetails(@PathVariable("itemId") int itemId,
                                 @SessionAttribute(value = "username", required = false) String username,
                                 Model model) {

        if (username != null) {
            int userId = userService.getUserId(username);
            recommendationService.saveTrackedItem(userId, itemId);
        }

        ItemInfo itemDetails = itemService.getOneItemById(itemId);
        model.addAttribute("itemDetails", itemDetails);

        return "itemsDetails";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("itemid") int itemId,
                            @RequestParam("quantity") int quantity,
                            @SessionAttribute(value = "username", required = false) String username,
                            Model model) {

        if (username == null) {
            return "redirect:/login";
        }

        int userId = userService.getUserId(username);

        // Decrease stock
        itemService.decreaseNumberOfItem(itemId, quantity);

        // Create order
        orderService.processOrder(userId, itemId, quantity);

        // Add to shopping cart
        shoppingCartService.addItemsToShoppingCart(userId, itemId, quantity);

        // Track item
        recommendationService.saveTrackedItem(userId, itemId);

        return "redirect:/home";
    }
}
