package com.foodit.test.sample.controller;


import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantData;
import com.foodit.test.sample.service.RestaurantDataService;
import com.googlecode.objectify.Key;
import com.threewks.thundr.http.ContentType;
import com.threewks.thundr.http.HttpSupport;
import com.threewks.thundr.view.json.JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Provides access to order data, by restaurant(s) or in total
 */
public class OrderReportController {

    private final RestaurantDataService restaurantDataService;

    public OrderReportController(RestaurantDataService restaurantDataService) {
        this.restaurantDataService = restaurantDataService;
    }

    public JsonView orderCountReport(String restaurantName) throws IOException {
        List<Order> orders = restaurantDataService.getOrders(restaurantName);

        int size = orders.size();

        return new JsonView(size);
    }

    public JsonView salesValueReport(String restaurantName) throws IOException {
        List<Order> orders = restaurantDataService.getOrders(restaurantName);

        BigDecimal sales = new BigDecimal(0);

        for (Order order :orders){

        }

        return new JsonView(sales);

    }
}
