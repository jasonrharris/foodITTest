package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Holds MainMenu data
 */
public class MainMenuItem {

    private int id;

    private String name;

    private String description;

    private String category;

    private Map<String, BigDecimal> sizeAndPrice;

    private List<MainMenuItemOptions> mealTypeOptions;

    private BigDecimal startingFromPrice;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Map<String, BigDecimal> getSizeAndPrice() {
        return sizeAndPrice;
    }

    public List<MainMenuItemOptions> getMealTypeOptions() {
        return mealTypeOptions;
    }

    public BigDecimal getStartingFromPrice() {
        return startingFromPrice;
    }
}
