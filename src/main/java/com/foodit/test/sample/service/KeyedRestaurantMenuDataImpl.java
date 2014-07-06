package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.MainMenuItem;
import com.foodit.test.sample.entities.RestaurantItemAndCategory;
import com.foodit.test.sample.entities.RestaurantItemKey;
import com.googlecode.objectify.LoadResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Provides access to the Restaurant menu data by various keys
 */
public class KeyedRestaurantMenuDataImpl implements KeyedRestaurantMenuData {


    @Override
    public void initialise(Map<String, List<MainMenuItem>> restaurantNameToMenuItems) {
        for (Map.Entry<String, List<MainMenuItem>> restaurantItems : restaurantNameToMenuItems.entrySet()) {
            categoriseRestaurantsMenuItems(restaurantItems);
        }
    }

    @Override
    public RestaurantItemAndCategory getRestaurantItemDetails(RestaurantItemKey itemKey) {
        LoadResult<RestaurantItemAndCategory> id = ofy().load().type(RestaurantItemAndCategory.class).id(getRestaurantItemId(itemKey.getStoreId(), itemKey.getId()));
        RestaurantItemAndCategory now = id.now();
        if (now == null){
            now = new RestaurantItemAndCategory(itemKey.getStoreId(), itemKey.getId(), "UNKNOWN CATEGORY (no match for item "+itemKey.getId()+")", "UNKNOWN ITEM NAME (no match for item "+itemKey.getId()+")");
        }
        return now;
    }


    private void categoriseRestaurantsMenuItems(Map.Entry<String, List<MainMenuItem>> restaurantItems) {
        String restaurantName = restaurantItems.getKey();
        List<RestaurantItemAndCategory> items = new ArrayList<>();

        for (MainMenuItem menuItem : restaurantItems.getValue()) {
            RestaurantItemAndCategory item = new RestaurantItemAndCategory(restaurantName, menuItem.getId(), menuItem.getCategory(), menuItem.getName());
            items.add(item);
        }

        ofy().save().entities(items).now();

    }

    public static String getRestaurantItemId(String restaurant, int itemId) {
        return restaurant +"+"+ itemId;
    }

}
