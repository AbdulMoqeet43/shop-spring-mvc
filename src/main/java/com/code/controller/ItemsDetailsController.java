package com.code.controller;

import com.code.model.ItemInfo;
import com.code.model.Recommendation;
import com.code.model.ShoppingCart;
import com.code.service.ItemService;
import com.code.service.RecommendationService;
import com.code.service.ShoppingCartService;
import com.code.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsDetailsController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/details/{itemId}")
    public String showItemDetails(@PathVariable("itemId") int itemId, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            int userId = userService.getUserId(username);
            recommendationService.saveTrackedItem(userId, itemId);
        }

        ItemInfo item = itemService.getOneItemById(itemId);
        model.addAttribute("itemDetails", item);
        return "itemDetails"; // JSP page: itemDetails.jsp
    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        int userId = userService.getUserId(username);
        List<ShoppingCart> cartItems = shoppingCartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "cart"; // JSP page: cart.jsp
    }

    @GetMapping("/recommended")
    public String showRecommendedItems(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        int userId = userService.getUserId(username);
        List<Recommendation> recommendedItems = recommendationService.getRecommendedItems(userId);
        model.addAttribute("recommendedItems", recommendedItems);
        return "recommendedItems"; // JSP page: recommendedItems.jsp
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("itemId") int itemId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        int userId = userService.getUserId(username);
        shoppingCartService.addToCart(userId, itemId, quantity);
        return "redirect:/items/cart";
    }

    @PostMapping("/emptyCart")
    public String emptyCart(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            int userId = userService.getUserId(username);
            shoppingCartService.emptyCart(userId);
        }
        return "redirect:/items/cart";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate session
        request.getSession().invalidate();

        // Redirect to welcome page
        return "redirect:/";
    }
}
