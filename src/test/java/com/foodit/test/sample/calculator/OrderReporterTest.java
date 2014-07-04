package com.foodit.test.sample.calculator;

import com.foodit.test.sample.entities.*;
import com.foodit.test.sample.service.RestaurantDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReporterTest {
    private final int otherItem1 = 4;
    private final int otherItem2 = 2;
    @Mock
    private RestaurantDataService restaurantDataService;

    private OrderReporter orderReporter;
    private final int expectedMostPopularCategoryItem = 3;

    @Before
    public void setup() {
        orderReporter = new OrderReporter(restaurantDataService);
    }

    @Test
    public void shouldReturnNumberOfOrders() throws IOException {

        String restId = "BBQ";

        when(restaurantDataService.getOrders(restId)).thenReturn(Arrays.asList(new OrderBuilder().createOrder(), new OrderBuilder().createOrder()));

        assertThat(orderReporter.getNumberOfOrders(restId), equalTo(2));

    }
    
    @Test
    public void shouldCorrectlyDetermineTotalSales(){

        OrderBuilder orderBuilder = new OrderBuilder();

        BigDecimal totalValue1 = new BigDecimal(12.0);
        BigDecimal totalValue2 = new BigDecimal(11.0);
        BigDecimal totalValue3 = new BigDecimal(9.0);

        List<Order> orders = Arrays.asList(
                orderBuilder.setTotalValue(totalValue1).setOrderId(1).createOrder(),
                orderBuilder.setTotalValue(totalValue2).setOrderId(2).createOrder(),
                orderBuilder.setTotalValue(totalValue3).setOrderId(3).createOrder()
        );

        String restaurantName = "bbq";

        when(restaurantDataService.getOrders(restaurantName)).thenReturn(orders);

        BigDecimal totalSalesAmount = orderReporter.getTotalSalesAmount(restaurantName);

        assertThat(totalValue1.add(totalValue2).add(totalValue3).compareTo(totalSalesAmount), equalTo(0));

    }

    @Test
    public void shouldCorrectlyDetermineMostPopularCategory(){

        String rest1 = "rest1";
        String rest2 = "rest2";

        Order rest1Order = getOrder(rest1);

        Order rest2Order = getOrder(rest2);

        when(restaurantDataService.getAllRestaurantNames()).thenReturn(Arrays.asList(rest1, rest2));
        when(restaurantDataService.getOrders(rest1)).thenReturn(Arrays.asList(rest1Order));
        when(restaurantDataService.getOrders(rest2)).thenReturn(Arrays.asList(rest2Order));

        String mostPopularCat = "mostPopularCat";

        mockRestaurantCategoryLookup(rest1, mostPopularCat);
        mockRestaurantCategoryLookup(rest2, mostPopularCat);

        assertThat(orderReporter.getMostPopularCategory(rest1), equalTo(mostPopularCat));

    }

    private void mockRestaurantCategoryLookup(String rest, String mostPopularCat) {
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, otherItem1))).thenReturn("categpry1");
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, otherItem2))).thenReturn("categpry2");
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, expectedMostPopularCategoryItem))).thenReturn(mostPopularCat);
    }

    private Order getOrder(String rest1) {
        OrderBuilder orderBuilder = new OrderBuilder();
        LineItemBuilder lineItemBuilder = new LineItemBuilder();
        List<LineItem> rest1LineItems = Arrays.asList(
                lineItemBuilder.setId(2).setQuantity(1).setId(otherItem1).createLineItem(),
                lineItemBuilder.setId(1).setQuantity(1).setId(otherItem2).createLineItem(),
                lineItemBuilder.setId(2).setQuantity(1).setId(expectedMostPopularCategoryItem).createLineItem(),
                lineItemBuilder.setId(1).setQuantity(1).setId(expectedMostPopularCategoryItem).createLineItem());

        return orderBuilder.setStoreId(rest1).setOrderId(1).setLineItems(rest1LineItems).createOrder();
    }
}