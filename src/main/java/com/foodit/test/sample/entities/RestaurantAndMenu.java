package com.foodit.test.sample.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Menu Structure
 */
public class RestaurantAndMenu {
    private String restaurantId;

    private Map<String, List<MainMenuItem>> menu;

    public List<MainMenuItem> getMainMenuItems(){
        List<MainMenuItem> items = new ArrayList<>();
        for(List<MainMenuItem> mainMenuItems : menu.values()){
            items.addAll(mainMenuItems);
        }
        return items;
    }

}
