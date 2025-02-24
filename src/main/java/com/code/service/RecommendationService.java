package com.code.service;

import com.code.model.Recommendation;
import com.code.model.RecommendationInfo;
import com.code.repository.RecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecommendationService {

    @Autowired
    private RecommendationRepo recommendationRepo;

    /**
     * Fetches the most frequently bought items.
     *
     * @return List of RecommendationInfo
     */
    public List<RecommendationInfo> getMostBuyingItems() {
        return recommendationRepo.findMostBuyingItems()
                .stream()
                .map(this::convertToRecommendationInfo)
                .collect(Collectors.toList());
    }

    /**
     * Saves a tracked item that a user has interacted with.
     *
     * @param userId The user's ID
     * @param itemId The item's ID
     */
    public void saveTrackedItem(int userId, int itemId) {
        validateInputs(userId, itemId);
        recommendationRepo.saveTrackedItem(userId, itemId);
    }

    /**
     * Fetches the most viewed items.
     *
     * @return List of RecommendationInfo
     */
    public List<RecommendationInfo> getMostViewedItems() {
        return recommendationRepo.findMostViewedItems()
                .stream()
                .map(this::convertToRecommendationInfo)
                .collect(Collectors.toList());
    }

    // Converts Recommendation entity to RecommendationInfo DTO
    private RecommendationInfo convertToRecommendationInfo(Recommendation recommendation) {
        return new RecommendationInfo(
                recommendation.getId(),
                recommendation.getPrice(),
                recommendation.getDescription(),
                recommendation.getName()
        );
    }

    // Input validation
    private void validateInputs(int userId, int itemId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be greater than zero.");
        }
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be greater than zero.");
        }
    }

    public List<Recommendation> getRecommendedItems(int userId) {
        return recommendationRepo.findMostBuyingItems();
    }
}
