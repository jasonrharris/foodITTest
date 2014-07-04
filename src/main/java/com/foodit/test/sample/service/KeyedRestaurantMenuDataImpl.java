package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.MainMenuItem;
import com.foodit.test.sample.entities.RestaurantItemKey;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides access to the Restaurant menu data by various keys
 */
public class KeyedRestaurantMenuDataImpl implements KeyedRestaurantMenuData {

    private final Map<RestaurantItemKey, String> itemToCategoryMap = new ConcurrentHashMap<>();

    @Override
    public void initialise(Map<String, List<MainMenuItem>> restaurantNameToMenuItems){
        for (Map.Entry<String, List<MainMenuItem>> restaurantItems :restaurantNameToMenuItems.entrySet()){
            categoriseRestaurantsMenuItems(restaurantItems);
        }
    }

    private void categoriseRestaurantsMenuItems(Map.Entry<String, List<MainMenuItem>> restaurantItems) {
        String restaurantName = restaurantItems.getKey();
        for (MainMenuItem menuItem : restaurantItems.getValue()){
            RestaurantItemKey key = new RestaurantItemKey(restaurantName, menuItem.getId());
            itemToCategoryMap.put(key, menuItem.getCategory());
        }
    }

    @Override
    public void clear() {
        itemToCategoryMap.clear();
    }

    @Override
    public String getCategoryByRestaurantItemKey(RestaurantItemKey itemKey) {
        return itemToCategoryMap.get(itemKey);
    }
}
