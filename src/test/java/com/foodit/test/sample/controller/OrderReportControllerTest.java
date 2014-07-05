package com.foodit.test.sample.controller;

import com.foodit.test.sample.calculator.OrderReporter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReportControllerTest {

    @Mock
    private OrderReporter orderReporter;

    private OrderReportController controller;
    public static final String REST_ID = "BBQ";

    @Before
    public void setup(){
        controller = new OrderReportController(orderReporter);
    }

    @Test
    public void shouldReturnNumberOfOrders() throws IOException {

        int expectedNumberOfOrders = 2;

        when(orderReporter.getNumberOfOrders(REST_ID)).thenReturn(expectedNumberOfOrders);

        assertThat((int) controller.orderCountReport(REST_ID).getOutput(), equalTo(expectedNumberOfOrders));

    }

    @Test
    public void shouldReturnTotalSales() throws IOException {
        BigDecimal expectedSales = new BigDecimal(10.10);
        when(orderReporter.getTotalSalesAmount(REST_ID)).thenReturn(expectedSales);
        assertThat((BigDecimal) controller.orderSalesReport(REST_ID).getOutput(), equalTo(expectedSales));
    }

    @Test
    public void shouldReturnMostPopularCategory() throws IOException {
        String expectedMostPopCat = "kebabs";
        when(orderReporter.getMostPopularCategory(REST_ID)).thenReturn(expectedMostPopCat);
        assertThat((String) controller.mostPopularCategoryReport(REST_ID).getOutput(), equalTo(expectedMostPopCat));
    }

    @Test
    public void shouldReturnMostFrequentlyOrderedItemsPerRestaurant() throws IOException {
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put(REST_ID, 6);
        expectedMap.put("rest2", 4);
        when(orderReporter.getMostFrequentlyOrderedItemPerRestaurant()).thenReturn(expectedMap);
        assertThat((Map<String, Integer>) controller.mostPopularItemByRestaurant().getOutput(), equalTo(expectedMap));
    }


}