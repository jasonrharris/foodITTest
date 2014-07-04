package com.foodit.test.sample.entities;

import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SideMenuItemTest {

    @Test
    public void shouldUnmarshalJSONCorrectly(){
        String label = "Chips";
        String size = "Regular";
        String price = "1.50";
        String json = "{\"label\":\"" + label + "\",\"pricesForSize\":{\"" + size + "\":\"" + price + "\"}}";

        Gson gson = new Gson();

        SideMenuItem sideMenuItem = gson.fromJson(json, SideMenuItem.class);

        assertThat(sideMenuItem.getLabel(), equalTo(label));

        assertThat(sideMenuItem.getPricesForSize().get(size), equalTo(new BigDecimal(price)));
    }

}