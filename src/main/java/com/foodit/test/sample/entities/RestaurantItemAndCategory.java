package com.foodit.test.sample.entities;

import com.foodit.test.sample.service.KeyedRestaurantMenuDataImpl;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
* MAkes checking item data from id easier.
*/
@Entity
public class RestaurantItemAndCategory {
    @Id
    private String storeIdPlusItemId;
    private String category;
    private String name;

    public RestaurantItemAndCategory() {
    }

    public RestaurantItemAndCategory(String restaurant, int itemId, String category, String name) {
        this.category = category;
        this.name = name;
        storeIdPlusItemId = KeyedRestaurantMenuDataImpl.getRestaurantItemId(restaurant, itemId);
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantItemAndCategory that = (RestaurantItemAndCategory) o;

        if (!category.equals(that.category)) return false;
        if (!storeIdPlusItemId.equals(that.storeIdPlusItemId)) return false;

        return true;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return storeIdPlusItemId.hashCode();
    }
}
