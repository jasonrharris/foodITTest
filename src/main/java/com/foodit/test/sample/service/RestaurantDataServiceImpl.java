package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.MainMenuItem;
import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantData;
import com.foodit.test.sample.entities.RestaurantItemKey;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.google.common.base.Optional;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.Key;
import com.threewks.thundr.logger.Logger;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * A service for loading and retrieving with RestaurantData
 */
public class RestaurantDataServiceImpl implements RestaurantDataService {

    private final List<String> restaurantNames = new Vector<>();

    private final KeyedRestaurantMenuDataImpl keyedRestaurantMenuData = new KeyedRestaurantMenuDataImpl();

    @Override
    public RestaurantData getRestaurantDataByName(String restaurantName) {
        Optional<RestaurantData> restaurantLoadData = getLatestRestaurantData(restaurantName);
        return restaurantLoadData.isPresent() ? restaurantLoadData.get() : new RestaurantData();
    }

    @Override
    public void loadData(List<String> allRestaurantNames) {
        List<RestaurantData> allRestaurantData = Lists.newArrayList();

        Map<String, List<MainMenuItem>> restaurantToMainItemsMap = new HashMap<>();

        restaurantNames.clear();
        for (String restaurant : allRestaurantNames) {
            RestaurantData restaurantData = loadData(restaurant);

            loadMenu(restaurantToMainItemsMap, restaurant, restaurantData);

            allRestaurantData.add(restaurantData);

            restaurantNames.add(restaurant);
        }
        keyedRestaurantMenuData.initialise(restaurantToMainItemsMap);

        ofy().save().entities(allRestaurantData);
    }

    private void loadMenu(Map<String, List<MainMenuItem>> restaurantToMainItemsMap, String restaurant, RestaurantData restaurantData) {
        List<MainMenuItem> menu = getMenu(restaurantData);

        restaurantToMainItemsMap.put(restaurant, menu);
    }

    @Override
    public List<Order> getOrders(String restaurantName) {
        Optional<RestaurantData> restaurantLoadData = getLatestRestaurantData(restaurantName);

        if (restaurantLoadData.isPresent()) {
            RestaurantData restaurantData = restaurantLoadData.get();
            Text ordersJson = restaurantData.getOrdersJson();
            Gson gson = new Gson();
            Type orderListType = new TypeToken<List<Order>>() {
            }.getType();
            return gson.fromJson(ordersJson.getValue(), orderListType);
        }
        return Collections.emptyList();

    }

    @Override
    public String getCategoryByRestaurantItem(RestaurantItemKey restaurantItemKey) {
        return keyedRestaurantMenuData.getCategoryByRestaurantItemKey(restaurantItemKey);
    }

    @Override
    public List<String> getAllRestaurantNames() {
        return restaurantNames;
    }

    private List<MainMenuItem> getMenu(RestaurantData restaurantData) {

        Text menuJson = restaurantData.getMenuJson();
        Gson gson = new Gson();
        Type mainMenuListType = new TypeToken<List<MainMenuItem>>() {}.getType();

        return gson.fromJson(menuJson.getValue(), mainMenuListType);
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
