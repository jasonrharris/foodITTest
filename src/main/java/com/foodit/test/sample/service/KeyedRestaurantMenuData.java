package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.MainMenuItem;
import com.foodit.test.sample.entities.RestaurantItemAndCategory;
import com.foodit.test.sample.entities.RestaurantItemKey;

import java.util.List;
import java.util.Map;

/**
 * Provide access to Restaurant Data by various keys
 */
public interface KeyedRestaurantMenuData {

    void initialise(Map<String, List<MainMenuItem>> restaurantNameToMenuItems);

    RestaurantItemAndCategory getRestaurantItemDetails(RestaurantItemKey itemKey);
}
