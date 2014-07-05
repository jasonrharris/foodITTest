package com.foodit.test.sample.entities;

import java.util.List;

/**
 * Details re. options available for a meal and how the can be selected
 */
public class MainMenuItemOptions {
    private String name;

    private boolean multiSelect;

    private boolean dropDown;

    private List<SideMenuItem> options;

    public MainMenuItemOptions() {
    }

    public MainMenuItemOptions(String name, boolean multiSelect, boolean dropDown, List<SideMenuItem> options) {
        this.name = name;
        this.multiSelect = multiSelect;
        this.dropDown = dropDown;
        this.options = options;
    }

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
