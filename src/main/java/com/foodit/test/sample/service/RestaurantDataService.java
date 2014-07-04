package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.RestaurantData;

/**
 * A service for interacting with Restaurant Data.
 */
public interface RestaurantDataService {
    RestaurantData getRestaurantDataByName(String restaurant);
}
