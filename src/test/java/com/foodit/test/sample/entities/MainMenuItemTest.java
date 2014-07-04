package com.foodit.test.sample.entities;

import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class MainMenuItemTest {

    @Test
    public void shouldUnmarshallJSON(){
        String name = "Mixed Kebab";
        int id = 5;
        String size = "Regular";
        String price = "7.50";
        String json = "{" +
                "\"id\":" + id + "," +
                "\"name\":\""+name+"\"," +
                "\"description\":\"\"," +
                "\"category\":\"Kebabs\"," +
                "\"sizeAndPrice\":{\"" + size + "\":\"" + price + "\"}," +
                "\"mealTypeOptions\":[{\"name\":\"Side\",\"multiSelect\":true,\"dropDown\":false,\"options\":[{\"label\":\"Chips\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Roast Potatoes\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Rice\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Taramasalata\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Houmous\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Tzatziki\",\"pricesForSize\":{\"Regular\":\"1.50\"}},{\"label\":\"Greek Salad\",\"pricesForSize\":{\"Regular\":\"3.00\"}}]}],"+
                "\"startingFromPrice\":\"7.50\"}";

        Gson gson = new Gson();

        MainMenuItem mainMenuItem = gson.fromJson(json, MainMenuItem.class);

        assertThat(mainMenuItem.getName(), equalTo(name));
        assertThat(mainMenuItem.getId(), equalTo(id));

        assertThat(mainMenuItem.getSizeAndPrice().get(size), equalTo(new BigDecimal(price)));

        assertThat(mainMenuItem.getMealTypeOptions().size(), equalTo(1));
    }
}