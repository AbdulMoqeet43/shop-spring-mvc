package com.code.controller;

import com.code.model.ItemInfo;
import com.code.service.ItemService;
import com.code.service.ShoppingCartService;
import com.code.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "welcome";  // JSP will be resolved from /WEB-INF/views/welcome.jsp
    }

    @GetMapping
    public String homePage(HttpSession session, Model model) throws UnsupportedEncodingException {
        String user = (String) session.getAttribute("username");

        if (user == null) {
            return "redirect:/welcome?error=Please log in first";
        }

        List<ItemInfo> availableItemsInfo = itemService.getAllItems();
        model.addAttribute("username", user);
        model.addAttribute("availableItems", availableItemsInfo);
        return "items";  // Forward to items.jsp
    }

    @PostMapping
    public String handleActions(@RequestParam("action") String action, HttpSession session) throws UnsupportedEncodingException {
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/welcome?error=Please login first";
        }

        String user = (String) session.getAttribute("username");

        int userId = userService.getUserId(user);

        if (action == null || action.isEmpty()) {
            return "redirect:/unauthorized";
        }

        switch (action) {
            case "View Cart":
                return "redirect:/cart";

            case "Empty Cart":
                shoppingCartService.deleteAllItemsFromCart(userId);
                return "redirect:/home?message=Cart emptied successfully";

        }
        return user;
    }
}
