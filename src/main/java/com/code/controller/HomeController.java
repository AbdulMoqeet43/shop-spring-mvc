package com.code.controller;

import com.code.model.ItemInfo;
import com.code.service.ItemService;
import com.code.service.ShoppingCartService;
import com.code.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    private final ItemService itemService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public HomeController(ItemService itemService, ShoppingCartService shoppingCartService, UserService userService) {
        this.itemService = itemService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";  // Redirects to welcome.jsp
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        String user = (String) session.getAttribute("username");

        if (user == null) {
            return "redirect:/?error=" + encodeURL("Please log in first");
        }

        List<ItemInfo> availableItems = itemService.getAllItems();
        model.addAttribute("username", user);
        model.addAttribute("availableItems", availableItems);

        return "items"; // Loads items.jsp
    }


    @PostMapping
    public String handleActions(@RequestParam("action") String action, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/?error=" + encodeURL("Please login first");
        }

        String user = (String) session.getAttribute("username");
        int userId = userService.getUserId(user);

        switch (action) {
            case "View Cart":
                return "redirect:/cart";
            case "Empty Cart":
                shoppingCartService.deleteAllItemsFromCart(userId);
                return "redirect:/home?message=" + encodeURL("Cart emptied successfully");
            case "Recommended for You":
                return "redirect:/recommendations";
            case "Logout":
                session.invalidate();
                return "redirect:/";
            default:
                return "redirect:/home";
        }
    }

    private String encodeURL(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return message;
        }
    }
}
