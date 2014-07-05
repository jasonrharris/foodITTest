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

    public JsonView orderCountReport(String restaurant) throws IOException {
        return wrapInJsonView(orderReporter.getNumberOfOrders(restaurant));
    }

    public JsonView orderSalesReport(String restaurant) throws IOException {
        return wrapInJsonView(orderReporter.getTotalSalesAmount(restaurant));
    }

    public JsonView mostPopularCategoryReport(String restaurant) throws IOException {

        return wrapInJsonView(orderReporter.getMostPopularCategory(restaurant));
    }

    public JsonView mostPopularItemByRestaurant() throws IOException {

        return wrapInJsonView(orderReporter.getMostFrequentlyOrderedItemPerRestaurant());
    }

    private JsonView wrapInJsonView(Object numberOfOrders) {
        return new JsonView(numberOfOrders);
    }
}
