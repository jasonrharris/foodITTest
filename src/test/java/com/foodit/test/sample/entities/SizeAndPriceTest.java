package com.foodit.test.sample.entities;

import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SizeAndPriceTest {

    @Test
    public void shouldConvertJSONIntoSizeAndPrice(){
        String price = "1.50";
        String size = "Regular";
        String json = "{\"" + size + "\":\"" + price + "\"}";

        Gson gson = new Gson();

        SizeAndPrice sizeAndPrice = gson.fromJson(json, SizeAndPrice.class);

        assertThat(sizeAndPrice.getSize(), equalTo(size));
        assertThat(sizeAndPrice.getPrice(), equalTo(new BigDecimal(price)));

    }
}