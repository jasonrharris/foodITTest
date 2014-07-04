package com.foodit.test.sample.controller;

import com.foodit.test.sample.calculator.OrderReporter;
import com.foodit.test.sample.entities.OrderBuilder;
import com.foodit.test.sample.service.RestaurantDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReportControllerTest {

    @Mock
    private OrderReporter orderReporter;

    private OrderReportController controller;

    @Before
    public void setup(){
        controller = new OrderReportController(orderReporter);
    }

    @Test
    public void shouldReturnNumberOfOrders() throws IOException {

        String restId = "BBQ";

        when(orderReporter.getNumberOfOrders(restId)).thenReturn(2);

        assertThat((int) controller.orderCountReport(restId).getOutput(), equalTo(2));

    }



}