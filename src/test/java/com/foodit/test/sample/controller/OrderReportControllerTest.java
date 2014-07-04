package com.foodit.test.sample.controller;

import com.foodit.test.sample.entities.Order;
import com.foodit.test.sample.service.RestaurantDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReportControllerTest {
    @Mock
    private RestaurantDataService restaurantDataService;

    @Mock
    private HttpServletResponse response;

    private OrderReportController controller;

    @Before
    public void setup(){
        controller = new OrderReportController(restaurantDataService) ;
    }

    @Test
    public void shouldReturnNumberOfOrders() throws IOException {

        when(restaurantDataService.getOrders("BBQ")).thenReturn(Arrays.asList(new Order(), new Order()));

        assertThat((int) controller.orderCountReport("BBQ").getOutput(), equalTo(2));

    }

}