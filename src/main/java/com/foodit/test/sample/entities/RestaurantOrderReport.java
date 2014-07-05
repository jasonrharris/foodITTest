package com.foodit.test.sample.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Report Data to be stored in app engine
 */
@Entity
public class RestaurantOrderReport {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantOrderReport.class);
    @Id
    private String restaurantName;

    private String totalSales;

    private int orderCount;

    private String mostPopularCategory;

    private int mostFrequentlyOrderedItem;

    public RestaurantOrderReport() {
    }

    public RestaurantOrderReport(String name) {
        LOGGER.info("Constructing "+name + " report data");
        this.restaurantName = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public BigDecimal getTotalSales() {
        return StringUtils.isEmpty(totalSales)?null:new BigDecimal(totalSales);
    }

    public int getOrderCount() {
        return orderCount;
    }

    public String getMostPopularCategory() {
        return mostPopularCategory;
    }

    public int getMostFrequentlyOrderedItem() {
        return mostFrequentlyOrderedItem;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales.toPlainString();
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setMostPopularCategory(String mostPopularCategory) {
        this.mostPopularCategory = mostPopularCategory;
    }

    public void setMostFrequentlyOrderedItem(int mostFrequentlyOrderedItem) {
        this.mostFrequentlyOrderedItem = mostFrequentlyOrderedItem;
    }
}
