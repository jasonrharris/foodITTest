package com.foodit.test.sample.entities;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainMenuItemBuilderTest {

    @Test
    public void testSetSizeAndPrice() throws Exception {
        MainMenuItemBuilder builder = new MainMenuItemBuilder();
        Map<String, String> mapSizeAndPrice = new HashMap<>();
        String key = "key";
        String value = "10.10";
        mapSizeAndPrice.put(key, value);
        builder.setSizeAndPrice(mapSizeAndPrice);

        assertThat(builder.createMainMenuItem().getSizeAndPrice().get(key), equalTo(new BigDecimal(value)));

    }
}