package com.foodit.test.sample.calculator;

import com.foodit.test.sample.entities.LineItem;
import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.entities.RestaurantItemKey;
import com.foodit.test.sample.service.RestaurantDataService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Will calculate sales
 */
public class OrderReporter {
    private final RestaurantDataService restaurantDataService;

    public OrderReporter(RestaurantDataService restaurantDataService) {
        this.restaurantDataService = restaurantDataService;
    }

    public BigDecimal getTotalSalesAmount(String restaurantName) {
        List<Order> orders = getOrders(restaurantName);
        BigDecimal sales = BigDecimal.ZERO;
        for (Order order : orders) {
            sales = addToCumulativeTotal(sales, order);
        }
        return sales;
    }

    public String getMostPopularCategory(String restaurantName) {
        List<Order> orders = getOrders(restaurantName);

        Map<String, Integer> categoryCounterMap = new HashMap<>();

        int maxCount = 0;
        String mostPopCategory = "";

        for (Order order : orders) {
            for (LineItem lineItem : order.getLineItems()) {
                org.apache.commons.lang3.tuple.Pair<String, Integer> categoryToCount = incrementCategoryCount(restaurantName, categoryCounterMap, lineItem);
                Integer newCount = categoryToCount.getValue();
                if (maxCount < newCount){
                    maxCount = newCount;
                    mostPopCategory = categoryToCount.getKey();
                }
            }
        }
        return mostPopCategory;
    }

    public int getNumberOfOrders(String restaurantName) {
        return getOrders(restaurantName).size();
    }

    private List<Order> getOrders(String restaurantName) {
        return restaurantDataService.getOrders(restaurantName);
    }

    private org.apache.commons.lang3.tuple.Pair<String, Integer> incrementCategoryCount(String restaurantName, Map<String, Integer> categoryCounterMap, LineItem lineItem) {
        int id = lineItem.getId();
        String category = restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(restaurantName, id));
        Integer count = categoryCounterMap.get(category);
        int newCount = count == null ? 1 : count + 1;
        categoryCounterMap.put(category, newCount);
        return org.apache.commons.lang3.tuple.Pair.of(category, newCount);
    }

    private BigDecimal addToCumulativeTotal(BigDecimal sales, Order order) {
        sales = sales.add(order.getTotalValue());
        return sales;
    }
}
