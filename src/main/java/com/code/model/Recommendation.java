package com.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    private int id;
    private String name;
    private String description;
    private double price;
    private int userId;
    private int itemId;

    public Recommendation(int id, String name, String description, double price) {
    }
}
