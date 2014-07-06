package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantData;
import com.foodit.test.sample.entities.RestaurantItemKey;

import java.util.List;
import java.util.Map;

/**
 * A service for interacting with Restaurant Data.
 */
public interface RestaurantDataService {

    RestaurantData getRestaurantDataByName(String restaurant);

    void loadData(List<String> restaurants);

    List<Order> getOrders(String restaurantName);

    String getCategoryByRestaurantItem(RestaurantItemKey restaurantItemKey);

    List<String> getAllRestaurantNames();

    String getNameOfItem(String restaurantName, int itemId);
}
