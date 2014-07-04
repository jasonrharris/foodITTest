package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantData;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.google.common.base.Optional;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.threewks.thundr.logger.Logger;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * A service for loading and retrieving with RestaurantData
 */
public class RestaurantDataServiceImpl implements RestaurantDataService {
    @Override
    public RestaurantData getRestaurantDataByName(String restaurantName) {
        Optional<RestaurantData> restaurantLoadData = getLatestRestaurantData(restaurantName);
        return restaurantLoadData.isPresent() ? restaurantLoadData.get() : new RestaurantData();
    }

    @Override
    public void loadData(List<String> restaurants) {
        List<RestaurantData> restaurantData = Lists.newArrayList();
        for (String restaurant : restaurants) {
            restaurantData.add(loadData(restaurant));
        }
        ofy().save().entities(restaurantData);
    }

    @Override
    public List<Order> getOrders(String restaurantName) {
        Optional<RestaurantData> restaurantLoadData = getLatestRestaurantData(restaurantName);

        if (restaurantLoadData.isPresent()){
            RestaurantData restaurantData = restaurantLoadData.get();
            Text ordersJson = restaurantData.getOrdersJson();
            Gson gson = new Gson();
            Type orderListType = new TypeToken<List<Order>>() {}.getType();
            return gson.fromJson(ordersJson.getValue(), orderListType);
        }
        return Collections.emptyList();

    }

    private RestaurantData loadData(String restaurantName) {
        String orders = readFile(String.format("orders-%s.json", restaurantName));
        String menu = readFile(String.format("menu-%s.json", restaurantName));
        return new RestaurantData(restaurantName, menu, orders);
    }

    private String readFile(String resourceName) {
        URL url = Resources.getResource(resourceName);
        try {
            return IOUtils.toString(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Optional<RestaurantData> getLatestRestaurantData(String restaurantName) {
        RestaurantData now = ofy().load().key(Key.create(RestaurantData.class, restaurantName)).now();
        return Optional.fromNullable(now);
    }


}
