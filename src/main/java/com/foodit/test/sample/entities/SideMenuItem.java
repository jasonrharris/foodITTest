package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Stores data relating to Side Items
 */
public class SideMenuItem {
    private String label;
    private Map<String, BigDecimal> pricesForSize;

    public SideMenuItem() {
    }

    public String getLabel() {
        return label;
    }

    public Map<String, BigDecimal> getPricesForSize() {
        return pricesForSize;
    }
}
