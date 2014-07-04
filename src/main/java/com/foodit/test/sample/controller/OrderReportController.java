package com.foodit.test.sample.controller;


import com.foodit.test.sample.calculator.OrderReporter;
import com.threewks.thundr.view.json.JsonView;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Provides access to order data, by restaurant(s) or in total
 */
public class OrderReportController {


    private final OrderReporter orderReporter;

    public OrderReportController(OrderReporter orderReporter) {
        this.orderReporter = orderReporter;
    }

    public JsonView orderCountReport(String restaurantName) throws IOException {
        int size = orderReporter.getNumberOfOrders(restaurantName);

        return new JsonView(size);
    }

    public JsonView orderSalesReport(String restaurantName) throws IOException {

        BigDecimal sales = orderReporter.getTotalSalesAmount(restaurantName);

        return new JsonView(sales);

    }

    public JsonView mostPopularCategoryReport(String restaurantName) throws IOException {

        return new JsonView(orderReporter.getMostPopularCategory(restaurantName));
    }
}
