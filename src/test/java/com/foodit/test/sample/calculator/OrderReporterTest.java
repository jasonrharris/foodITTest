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
import org.mockito.verification.VerificationMode;

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
    private final int otherItem1 = 4;
    private final int otherItem2 = 2;
    @Mock
    private RestaurantDataService restaurantDataService;

    private OrderReporter orderReporter;
    private final int expectedMostPopularCategoryItem = 3;

    @Rule
    public SetupAppengine setupAppengine = new SetupAppengine();
    @Rule
    public SetupObjectify setupObjectify = new SetupObjectify(RestaurantOrderReport.class);

    @Before
    public void setup() {
        orderReporter = new OrderReporter(restaurantDataService);
    }

    @Test
    public void shouldReturnCorrectNumberOfOrdersWhenCalledTwice() throws IOException {

        String restId = "BBQ";

        when(restaurantDataService.getOrders(restId)).thenReturn(Arrays.asList(new OrderBuilder().createOrder(), new OrderBuilder().createOrder()));

        assertThat(orderReporter.getNumberOfOrders(restId), equalTo(2));

        //ensure 2nd request returns s
        assertThat(orderReporter.getNumberOfOrders(restId), equalTo(2));

        //verify(restaurantDataService, times(1)).getOrders(restId);

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

        Order rest1Order = getCategorisedOrder(rest1);

        Order rest2Order = getCategorisedOrder(rest2);

        when(restaurantDataService.getOrders(rest1)).thenReturn(Arrays.asList(rest1Order));
        when(restaurantDataService.getOrders(rest2)).thenReturn(Arrays.asList(rest2Order));

        String mostPopularCat = "mostPopularCat";

        mockRestaurantCategoryLookup(rest1, mostPopularCat);
        mockRestaurantCategoryLookup(rest2, mostPopularCat);

        assertThat(orderReporter.getMostPopularCategory(rest1), equalTo(mostPopularCat));

    }

    @Test public void shouldGetMostFrequentlyOrderedItemPerRestaurant(){

        String rest1 = "rest1";
        String rest2 = "rest2";

        LocalDateTime now = LocalDateTime.now();

        Order rest1Order1 = getTimedOrder(rest1, now.plusMinutes(1), 1);
        Order rest1Order2 = getTimedOrder(rest1, now.plusMinutes(2), 2);

        Order rest2Order1 = getTimedOrder(rest2, now.plusMinutes(3), 1);
        Order rest2Order2 = getTimedOrder(rest2, now.plusMinutes(4), 2);

        when(restaurantDataService.getAllRestaurantNames()).thenReturn(Arrays.asList(rest1, rest2));
        when(restaurantDataService.getOrders(rest1)).thenReturn(Arrays.asList(rest1Order1, rest1Order2));
        when(restaurantDataService.getOrders(rest2)).thenReturn(Arrays.asList(rest2Order1, rest2Order2));

        Map<String, Integer> mostPopularItemPerRestaurant = orderReporter.getMostFrequentlyOrderedItemPerRestaurant();

        assertThat(mostPopularItemPerRestaurant.get(rest1), equalTo(expectedMostPopularCategoryItem));
        assertThat(mostPopularItemPerRestaurant.get(rest2), equalTo(expectedMostPopularCategoryItem));


    }

    private void mockRestaurantCategoryLookup(String rest, String mostPopularCat) {
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, otherItem1))).thenReturn("categpry1");
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, otherItem2))).thenReturn("category2");
        when(restaurantDataService.getCategoryByRestaurantItem(new RestaurantItemKey(rest, expectedMostPopularCategoryItem))).thenReturn(mostPopularCat);
    }

    private Order getCategorisedOrder(String rest1) {
        OrderBuilder orderBuilder = new OrderBuilder();
        List<LineItem> rest1LineItems = getLineItems();

        return orderBuilder.setStoreId(rest1).setOrderId(1).setLineItems(rest1LineItems).createOrder();
    }

    private Order getTimedOrder(String rest1, LocalDateTime orderTime, int orderId) {
        OrderBuilder orderBuilder = new OrderBuilder();
        List<LineItem> rest1LineItems = getLineItems();

        return orderBuilder.setStoreId(rest1).setOrderId(orderId).setCreated(orderTime.toString()).setLineItems(rest1LineItems).createOrder();
    }

    private List<LineItem> getLineItems() {
        LineItemBuilder lineItemBuilder = new LineItemBuilder();
        return Arrays.asList(
                lineItemBuilder.setQuantity(1).setId(otherItem1).createLineItem(),
                lineItemBuilder.setQuantity(1).setId(otherItem2).createLineItem(),
                lineItemBuilder.setQuantity(1).setId(expectedMostPopularCategoryItem).createLineItem(),
                lineItemBuilder.setQuantity(1).setId(expectedMostPopularCategoryItem).createLineItem());
    }


}