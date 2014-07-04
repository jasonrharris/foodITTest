package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.RestaurantData;
import com.google.common.base.Optional;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * A service for interacting with RestaurantData
 */
public class RestaurantDataServiceImpl implements RestaurantDataService {
    @Override
    public RestaurantData getRestaurantDataByName(String restaurant) {
        Optional<RestaurantData> restaurantLoadData = getLatestRestaurantData(restaurant);
        return restaurantLoadData.isPresent() ? restaurantLoadData.get() : new RestaurantData();
    }

    private Optional<RestaurantData> getLatestRestaurantData(String restaurant) {
        RestaurantData now = ofy().load().key(Key.create(RestaurantData.class, restaurant)).now();
        return Optional.fromNullable(now);
    }
}
