package com.foodit.test.sample.entities;


import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LineItemTest {

    @Test
    public void shouldUnmarshalLineItem(){
        int id = 5;
        String total = "7.50";
        int quantity = 1;
        String unitPrice = "7.50";
        String json = "{\"id\":" + id + ",\"total\":\"" + total + "\",\"unitPrice\":\"" + unitPrice + "\",\"quantity\":" + quantity + ",\"promotion\":false,\"mealOptions\":[]}";

        Gson gson = new Gson();

        LineItem lineItem = gson.fromJson(json, LineItem.class);

        assertThat(lineItem.getId(), equalTo(id));

        assertThat(lineItem.getTotal(), equalTo(new BigDecimal(total)));

        assertThat(lineItem.getQuantity(), equalTo(quantity));

        assertThat(lineItem.getUnitPrice(), equalTo(new BigDecimal(unitPrice)));




    }

}