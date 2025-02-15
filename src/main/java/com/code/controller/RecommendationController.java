package com.code.controller;

import com.code.model.RecommendationInfo;
import com.code.service.RecommendationService;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class RecommendationController {

    @Autowired

    private RecommendationService recommendationService;
    @Autowired
    private UserService userService;

    @GetMapping("/recommendations")
    public String showRecommendations(Model model) {
        List<RecommendationInfo> availableRecommendations = recommendationService.getMostBuyingItems();
        List<RecommendationInfo> availableSeenRecommendations = recommendationService.getMostViewedItems();

        model.addAttribute("availableRecommendations", availableRecommendations);
        model.addAttribute("availableSeenRecommendations", availableSeenRecommendations);

        return "recommendations"; // Forward to recommendations.jsp
    }

    @PostMapping("/user-id")
    public String getUserId(@SessionAttribute(name = "username", required = false) String username, Model model) {
        if (username == null) {
            return "redirect:/login"; // Redirect to login if session expired
        }

        int userId = userService.getUserId(username);
        model.addAttribute("userId", userId);
        return "user-dashboard"; // Forward to user-dashboard.jsp
    }
}
