package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Hold
 */
public class MainMenuItem {

    private int id;

    private String name;

    private String description;

    private String category;

    private Map<String, BigDecimal> sizeAndPrice;

    private List<MainMenuItemOptions> mealTypeOptions;

    private BigDecimal startingFromPrice;

    public MainMenuItem(int id, String name, String description, String category, Map<String, BigDecimal> sizeAndPrice, List<MainMenuItemOptions> mealTypeOptions, BigDecimal startingFromPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.sizeAndPrice = sizeAndPrice;
        this.mealTypeOptions = mealTypeOptions;
        this.startingFromPrice = startingFromPrice;
    }

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

    public BigDecimal getStartingFromPrice() {return startingFromPrice;}


}
