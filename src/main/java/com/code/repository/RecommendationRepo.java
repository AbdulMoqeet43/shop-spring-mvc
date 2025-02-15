package com.code.repository;

import com.code.model.Recommendation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RecommendationRepo {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Fetch Most Purchased Items (From orders table)
     */
    public List<Recommendation> findMostBuyingItems() {
        String sql = """
            SELECT i.id, i.name, i.description, i.price 
            FROM items i
            JOIN orders o ON i.id = o.item_id
            GROUP BY i.id, i.name, i.description, i.price
            ORDER BY COUNT(o.item_id) DESC
            LIMIT 10
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Recommendation(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price")
        ));
    }

    /**
     * Fetch Most Viewed Items (From item_views table)
     */
    public List<Recommendation> findMostViewedItems() {
        String sql = """
            SELECT i.id, i.name, i.description, i.price 
            FROM items i
            JOIN item_views v ON i.id = v.item_id
            GROUP BY i.id, i.name, i.description, i.price
            ORDER BY COUNT(v.item_id) DESC
            LIMIT 10
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Recommendation(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price")
        ));
    }

    /**
     * Save a Tracked Item for a User (Into shoppingcart table)
     */
    public void saveTrackedItem(int userId, int itemId) {
        String sql = "INSERT INTO shoppingcart (user_id, item_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, itemId);
    }
}
