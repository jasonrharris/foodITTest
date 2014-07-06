package com.foodit.test.sample.calculator;

import com.foodit.test.sample.entities.*;
import com.foodit.test.sample.service.RestaurantDataService;
import com.threewks.thundr.gae.SetupAppengine;
import com.threewks.thundr.gae.objectify.SetupObjectify;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReporterTest {
    private final int otherItem1WithExtraQuantity = 4;
    private final int otherItem2 = 2;
    private final String otherCatWithExtraQuantity = "categpry1";
    @Mock
    private RestaurantDataService restaurantDataService;

    private OrderReporter orderReporter;
    private final int expectedMostPopularCategoryItem = 3;

    @Rule
    public SetupAppengine setupAppengine = new SetupAppengine();
    @Rule
    public SetupObjectify setupObjectify = new SetupObjectify(RestaurantOrderReport.class);
    private String expectedMostFrequentItem = "mostFreq";

    @Before
    public void setup() {
        orderReporter = new OrderReporter(restaurantDataService);
    }

    @Test
    public void shouldReturnCorrectNumberOfOrdersWhenCalledTwice() throws IOException {

        String restId = "BBQ";

        when(restaurantDataService.getOrders(restId)).thenReturn(Arrays.asList(new OrderBuilder().createOrder(), new OrderBuilder().createOrder()));

        assertThat(orderReporter.getNumberOfOrders(restId), equalTo(2));

        //ensure 2nd request returns same
        assertThat(orderReporter.getNumberOfOrders(restId), equalTo(2));

        verify(restaurantDataService, times(1)).getOrders(restId);

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

        orderReporter.getTotalSalesAmount(restaurantName);

        verify(restaurantDataService, times(1)).getOrders(restaurantName);

    }

    @Test
    public void shouldCorrectlyDetermineMostPopularCategory(){

        String rest1 = "rest1";
        String rest2 = "rest2";

        int quantity = 1;
        Order rest1Order = getCategorisedOrder(rest1, quantity);

        when(restaurantDataService.getOrders(rest1)).thenReturn(Arrays.asList(rest1Order));

        String mostPopularCat = "mostPopularCat";

        mockRestaurantCategoryLookup(rest1, mostPopularCat);
        mockRestaurantCategoryLookup(rest2, mostPopularCat);

        assertThat(orderReporter.getMostPopularCategory(rest1), equalTo(mostPopularCat));

        orderReporter.getMostPopularCategory(rest1);

        verify(restaurantDataService, times(1)).getOrders(rest1);

    }

    @Test
    public void shouldCorrectlyDetermineMostPopularCategoryBasedOnBiggerQuantity(){

        String rest1 = "rest1";
        String rest2 = "rest2";

        int quantity = 10;
        Order rest1Order = getCategorisedOrder(rest1, quantity);

        when(restaurantDataService.getOrders(rest1)).thenReturn(Arrays.asList(rest1Order));

        String mostPopularCat = "mostPopularCat";

        mockRestaurantCategoryLookup(rest1, mostPopularCat);
        mockRestaurantCategoryLookup(rest2, mostPopularCat);

        assertThat(orderReporter.getMostPopularCategory(rest1), equalTo(otherCatWithExtraQuantity));

        orderReporter.getMostPopularCategory(rest1);

        verify(restaurantDataService, times(1)).getOrders(rest1);

    }

    @Test public void shouldGetMostFrequentlyOrderedItemPerRestaurant(){

        String rest1 = "rest1";
        String rest2 = "rest2";

        LocalDateTime now = LocalDateTime.now();

        Order rest1Order1 = getTimedOrder(rest1, now.plusMinutes(1), 1, 1);
        Order rest1Order2 = getTimedOrder(rest1, now.plusMinutes(2), 2, 1);

        Order rest2Order1 = getTimedOrder(rest2, now.plusMinutes(3), 1, 1);
        Order rest2Order2 = getTimedOrder(rest2, now.plusMinutes(4), 2, 1);

        when(restaurantDataService.getAllRestaurantNames()).thenReturn(Arrays.asList(rest1, rest2));
        when(restaurantDataService.getOrders(rest1)).thenReturn(Arrays.asList(rest1Order1, rest1Order2));
        when(restaurantDataService.getOrders(rest2)).thenReturn(Arrays.asList(rest2Order1, rest2Order2));

        mockItemNameLookup(rest1);

        mockItemNameLookup(rest2);

        Map<String,String> mostPopularItemPerRestaurant = orderReporter.getMostFrequentlyOrderedItemPerRestaurant();

        assertThat(mostPopularItemPerRestaurant.get(rest1), equalTo(expectedMostFrequentItem));
        assertThat(mostPopularItemPerRestaurant.get(rest2), equalTo(expectedMostFrequentItem));

        orderReporter.getMostFrequentlyOrderedItemPerRestaurant();

        verify(restaurantDataService, times(1)).getOrders(rest1);
        verify(restaurantDataService, times(1)).getOrders(rest2);

    }

    private void mockRestaurantCategoryLookup(String rest, String mostPopularCat) {
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, otherItem1WithExtraQuantity))).thenReturn(otherCatWithExtraQuantity);
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, otherItem2))).thenReturn("category2");
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, expectedMostPopularCategoryItem))).thenReturn(mostPopularCat);
    }

    private void mockItemNameLookup(String rest) {
        when(restaurantDataService.getNameOfItem(rest, otherItem1WithExtraQuantity)).thenReturn("aName");
        when(restaurantDataService.getNameOfItem(rest, otherItem2)).thenReturn("anotherName");
        when(restaurantDataService.getNameOfItem(rest, expectedMostPopularCategoryItem)).thenReturn(expectedMostFrequentItem);
    }


    private Order getCategorisedOrder(String rest1, int quantity) {
        OrderBuilder orderBuilder = new OrderBuilder();
        List<LineItem> rest1LineItems = getLineItems(quantity);

        return orderBuilder.setStoreId(rest1).setOrderId(1).setLineItems(rest1LineItems).createOrder();
    }

    private Order getTimedOrder(String rest1, LocalDateTime orderTime, int orderId, int quantity) {
        OrderBuilder orderBuilder = new OrderBuilder();
        List<LineItem> rest1LineItems = getLineItems(quantity);

        return orderBuilder.setStoreId(rest1).setOrderId(orderId).setCreated(orderTime.toString()).setLineItems(rest1LineItems).createOrder();
    }

    private List<LineItem> getLineItems(int quantity) {
        LineItemBuilder lineItemBuilder = new LineItemBuilder();
        return Arrays.asList(
                lineItemBuilder.setQuantity(1).setId(otherItem1WithExtraQuantity).setQuantity(quantity).createLineItem(),
                lineItemBuilder.setQuantity(1).setId(otherItem2).createLineItem(),
                lineItemBuilder.setQuantity(1).setId(expectedMostPopularCategoryItem).createLineItem(),
                lineItemBuilder.setQuantity(1).setId(expectedMostPopularCategoryItem).createLineItem());
    }


}