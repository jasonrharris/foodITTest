package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantData;

import java.util.List;

/**
 * A service for interacting with Restaurant Data.
 */
public interface RestaurantDataService {
    RestaurantData getRestaurantDataByName(String restaurant);

    void loadData(List<String> restaurants);

    List<Order> getOrders(String restaurantName);
}
