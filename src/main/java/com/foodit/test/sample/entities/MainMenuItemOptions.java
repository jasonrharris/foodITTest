package com.foodit.test.sample.entities;

import java.util.List;

/**
 * Created by jasonrharris on 04/07/2014.
 */
public class MainMenuItemOptions {
    private String name;

    private boolean multiSelect;

    private boolean dropDown;

    private List<SideMenuItem> options;

    public String getName() {
        return name;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public boolean isDropDown() {
        return dropDown;
    }

    public List<SideMenuItem> getOptions() {
        return options;
    }
}
