package com.foodit.test.sample.calculator;

import com.foodit.test.sample.entities.*;
import com.foodit.test.sample.service.RestaurantDataService;
import com.googlecode.objectify.Key;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Will calculate sales
 */
public class OrderReporter {
    private final RestaurantDataService restaurantDataService;

    public OrderReporter(RestaurantDataService restaurantDataService) {
        this.restaurantDataService = restaurantDataService;
    }

    public BigDecimal getTotalSalesAmount(String restaurantName) {
        RestaurantOrderReport report = getStoredRestaurantOrderReport(restaurantName);

        if (report.getTotalSales() != null){
            return report.getTotalSales();
        }

        return calculateAndSaveTotalSalesAmount(restaurantName, report);
    }

    public Map<String, String> getMostFrequentlyOrderedItemPerRestaurant() {
        List<String> allRestaurantNames = restaurantDataService.getAllRestaurantNames();

        Map<String, String> restaurantToMostPopularItemMap = new HashMap<>();

        for (String restaurantName : allRestaurantNames) {
            addRestaurantsMostFrequentlyOrderedItem(restaurantToMostPopularItemMap, restaurantName);
        }

        return restaurantToMostPopularItemMap;

    }

    public String getMostPopularCategory(String restaurantName) {
        RestaurantOrderReport report = getStoredRestaurantOrderReport(restaurantName);

        if (StringUtils.isNotEmpty(report.getMostPopularCategory())){
            return report.getMostPopularCategory();
        }

        Pair<String, Integer> categoryCountPair = calculateAndSaveMostPopularCategory(restaurantName, report);

        return categoryCountPair.getKey();
    }

    public int getNumberOfOrders(String restaurantName) {
        RestaurantOrderReport report = getStoredRestaurantOrderReport(restaurantName);

        if (report.getOrderCount() > 0){
            return report.getOrderCount();
        }

        return calculateAndSaveNumberOfOrders(restaurantName, report);
    }

    private Pair<String, Integer> calculateAndSaveMostPopularCategory(String restaurantName, RestaurantOrderReport report) {
        Pair<String, Integer> categoryCountPair = calculateMostPopularCategory(restaurantName);

        report.setMostPopularCategory(categoryCountPair.getKey());

        saveReport(report);
        return categoryCountPair;
    }

    private void addRestaurantsMostFrequentlyOrderedItem(Map<String, String> restaurantToMostPopularItemMap, String restaurantName) {
        RestaurantOrderReport report = getStoredRestaurantOrderReport(restaurantName);

        if (StringUtils.isNotEmpty(report.getMostFrequentlyOrderedItem())){
            restaurantToMostPopularItemMap.put(restaurantName, report.getMostFrequentlyOrderedItem());
            return;
        }

        int mostFrequentlyOrderedItemId = calculateMostFrequentlyOrderedItem(restaurantName);

        String mostFreqOrderedItemName = restaurantDataService.getNameOfItem(restaurantName, mostFrequentlyOrderedItemId);
        report.setMostFrequentlyOrderedItem(mostFreqOrderedItemName);

        saveReport(report);

        restaurantToMostPopularItemMap.put(restaurantName, mostFreqOrderedItemName);
    }

    private int calculateMostFrequentlyOrderedItem(String restaurantName) {
        List<Order> orders = restaurantDataService.getOrders(restaurantName);

        Pair<RestaurantItemKey, Integer> itemCountPair = Pair.of(new RestaurantItemKey("", 0), 0);

        Map<RestaurantItemKey, Integer> itemCounterMap = new HashMap<>();

        for (Order order : orders) {
            itemCountPair = updateItemToCounterMapAndGetCurrentMaxItem(restaurantName, itemCountPair, itemCounterMap, order);
        }

        return itemCountPair.getKey().getId();
    }

    private void saveReport(RestaurantOrderReport report) {
        ofy().save().entity(report).now();
    }

    private RestaurantOrderReport getStoredRestaurantOrderReport(String restaurantName) {
        Key<RestaurantOrderReport> restaurantOrderReportKey = Key.create(RestaurantOrderReport.class, restaurantName);

        return getRestaurantOrderReport(restaurantOrderReportKey, restaurantName);
    }

    private BigDecimal calculateAndSaveTotalSalesAmount(String restaurantName, RestaurantOrderReport report) {
        List<Order> orders = getOrders(restaurantName);
        BigDecimal sales = BigDecimal.ZERO;
        for (Order order : orders) {
            sales = addOrderTotalToCumulativeTotal(sales, order);
        }
        report.setTotalSales(sales);
        saveReport(report);
        return sales;
    }

    private Pair<String, Integer> calculateMostPopularCategory(String restaurantName) {
        List<Order> orders = getOrders(restaurantName);

        Map<String, Integer> categoryCounterMap = new HashMap<>();

        Pair<String, Integer> categoryCountPair = Pair.of("",0);

        for (Order order : orders) {
            categoryCountPair = updateCategoryToCounterMapAndGetCurrentMaxCategory(restaurantName, categoryCounterMap, categoryCountPair, order);
        }
        return categoryCountPair;
    }

    private int calculateAndSaveNumberOfOrders(String restaurantName, RestaurantOrderReport report) {
        int numberOfOrders = getOrders(restaurantName).size();
        report.setOrderCount(numberOfOrders);
        saveReport(report);
        return numberOfOrders;
    }

    private RestaurantOrderReport getRestaurantOrderReport(Key<RestaurantOrderReport> restaurantOrderReportKey, String name) {
        return ofy().isLoaded(restaurantOrderReportKey)
                ? ofy().load().key(restaurantOrderReportKey).now()
                : new RestaurantOrderReport(name);
    }

    private Pair<RestaurantItemKey, Integer> updateItemToCounterMapAndGetCurrentMaxItem(
            String restaurantName,
            Pair<RestaurantItemKey, Integer> currentMaxCountPair,
            Map<RestaurantItemKey, Integer> itemCounterMap,
            Order order) {
        Pair<RestaurantItemKey, Integer> keyWithMaxCount = currentMaxCountPair;
        for (LineItem lineItem : order.getLineItems()) {
            int id = lineItem.getId();
            RestaurantItemKey item = new RestaurantItemKey(restaurantName, id);
            keyWithMaxCount = updatedKeyToCountPair(itemCounterMap, currentMaxCountPair, item, lineItem.getQuantity());
        }
        return keyWithMaxCount;
    }

    private Pair<String, Integer> updateCategoryToCounterMapAndGetCurrentMaxCategory(String restaurantName, Map<String, Integer> categoryCounterMap, Pair<String, Integer> categoryCountPair, Order order) {
        for (LineItem lineItem : order.getLineItems()) {
            String category = getCategoryByRestaurantItem(restaurantName, lineItem);
            categoryCountPair = updatedKeyToCountPair(categoryCounterMap, categoryCountPair, category, lineItem.getQuantity());
        }
        return categoryCountPair;
    }

    private <K> Pair<K, Integer> updatedKeyToCountPair(Map<K, Integer> keyToCounterMap, Pair<K, Integer> countPair, K key, int quantity) {
        Integer newCount = getKeyCountPair(keyToCounterMap, key, quantity);
        if (countPair.getValue() < newCount){
            countPair = Pair.of(key, newCount);
        }
        return countPair;
    }

    private String getCategoryByRestaurantItem(String restaurantName, LineItem lineItem) {
        return restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(restaurantName, lineItem.getId()));
    }

    private List<Order> getOrders(String restaurantName) {
        return restaurantDataService.getOrders(restaurantName);
    }

    private <K> Integer getKeyCountPair(Map<K, Integer> counterMap, K key, int quantity) {
        Integer count = counterMap.get(key);
        int newCount = count == null ? quantity : count + quantity;
        counterMap.put(key, newCount);
        return newCount;
    }

    private BigDecimal addOrderTotalToCumulativeTotal(BigDecimal sales, Order order) {
        sales = sales.add(order.getTotalValue());
        return sales;
    }
}
