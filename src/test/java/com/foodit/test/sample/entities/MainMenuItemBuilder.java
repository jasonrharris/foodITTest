package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MainMenuItemBuilder {
    private int id;
    private String name;
    private String description;
    private String category;
    private Map<String, BigDecimal> sizeAndPrice;
    private List<MainMenuItemOptions> mealTypeOptions;
    private BigDecimal startingFromPrice;

    public MainMenuItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public MainMenuItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MainMenuItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public MainMenuItemBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public MainMenuItemBuilder setSizeAndPrice(Map<String, BigDecimal> sizeAndPrice) {
        this.sizeAndPrice = sizeAndPrice;
        return this;
    }

    public MainMenuItemBuilder setMealTypeOptions(List<MainMenuItemOptions> mealTypeOptions) {
        this.mealTypeOptions = mealTypeOptions;
        return this;
    }

    public MainMenuItemBuilder setStartingFromPrice(BigDecimal startingFromPrice) {
        this.startingFromPrice = startingFromPrice;
        return this;
    }

    public MainMenuItem createMainMenuItem() {
        return new MainMenuItem(id, name, description, category, sizeAndPrice, mealTypeOptions, startingFromPrice);
    }
}