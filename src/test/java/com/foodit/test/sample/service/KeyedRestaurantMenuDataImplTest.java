package com.foodit.test.sample.service;

import com.foodit.test.sample.entities.MainMenuItem;
import com.foodit.test.sample.entities.MainMenuItemBuilder;
import com.foodit.test.sample.entities.RestaurantItemAndCategory;
import com.foodit.test.sample.entities.RestaurantItemKey;
import com.threewks.thundr.gae.SetupAppengine;
import com.threewks.thundr.gae.objectify.SetupObjectify;
import org.junit.Rule;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
public class KeyedRestaurantMenuDataImplTest {
    @Rule
    public SetupAppengine setupAppengine = new SetupAppengine();
    @Rule
    public SetupObjectify setupObjectify = new SetupObjectify(RestaurantItemAndCategory.class);

    private final int id1 = 1;
    private final int id2 = 2;
    private final String cat1 = "cat1";
    private final String cat2 = "cat2";

    private final KeyedRestaurantMenuDataImpl keyedRestaurantMenuData = new KeyedRestaurantMenuDataImpl();

    @Test
    public void shouldGetCategoryByRestaurantItemKey() throws Exception {

        Map<String, List<MainMenuItem>> restaurantNameToMenuItems = new HashMap<>();

        String rest1 = "rest1";
        restaurantNameToMenuItems.put(rest1, getMainMenuItems());

        String rest2 = "rest2";
        restaurantNameToMenuItems.put(rest2, getMainMenuItems());

        keyedRestaurantMenuData.initialise(restaurantNameToMenuItems);

        assertItemHasCorrectCategory(rest1, id1, cat1);

        assertItemHasCorrectCategory(rest1, id2, cat2);

        assertItemHasCorrectCategory(rest2, id1, cat1);

        assertItemHasCorrectCategory(rest2, id2, cat2);
    }

    private void assertItemHasCorrectCategory(String restaurant, int itemId, String expectedCategory) {
        RestaurantItemKey key = new RestaurantItemKey(restaurant, itemId);
        assertThat(keyedRestaurantMenuData.getRestaurantItemDetails(key).getCategory(), equalTo(expectedCategory));
    }

    private List<MainMenuItem> getMainMenuItems() {
        MainMenuItemBuilder mainMenuItemBuilder = new MainMenuItemBuilder();
        MainMenuItem mainMenuItem = mainMenuItemBuilder.setCategory(cat1).setId(id1).createMainMenuItem();
        MainMenuItem mainMenuItem2 = mainMenuItemBuilder.setCategory(cat2).setId(id2).createMainMenuItem();

        return Arrays.asList(mainMenuItem, mainMenuItem2);
    }
}