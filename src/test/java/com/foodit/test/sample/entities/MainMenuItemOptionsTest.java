package com.foodit.test.sample.entities;

import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class MainMenuItemOptionsTest {

    @Test
    public void shouldUnmarshalJSONCorrectly(){
        final String name = "Side";
        final boolean dropDown = false;
        final boolean multiSelect = true;
        String option1Label = "Chips";
        String option2Label = "Roast Potatoes";
        String size = "Regular";
        String price1 = "1.50";
        String price2 = "3.50";

        String json = "{\"name\":\"" + name + "\",\"multiSelect\":" + multiSelect + ",\"dropDown\":" + dropDown + "," +
                "\"options\":[" +
                "{\"label\":\"" + option1Label + "\",\"pricesForSize\":{\"" + size + "\":\"" + price1 + "\"}}," +
                "{\"label\":\"" + option2Label + "\",\"pricesForSize\":{\"" + size + "\":\"" + price2 + "\"}}]}";
        Gson gson = new Gson();

        MainMenuItemOptions mainMenuItemOptions = gson.fromJson(json, MainMenuItemOptions.class);

        assertThat(mainMenuItemOptions.getName(), equalTo(name));
        assertThat(mainMenuItemOptions.isDropDown(), equalTo(dropDown));
        assertThat(mainMenuItemOptions.isMultiSelect(), equalTo(multiSelect));

        List<SideMenuItem> options = mainMenuItemOptions.getOptions();

        assertThat(options.size(), equalTo(2));
        SideMenuItem sideMenuItem1 = options.get(0);
        assertThat(sideMenuItem1.getLabel(), equalTo(option1Label));
        assertThat(sideMenuItem1.getPricesForSize().get(size), equalTo(new BigDecimal(price1)));


        SideMenuItem sideMenuItem2 = options.get(1);
        assertThat(sideMenuItem2.getLabel(), equalTo(option2Label));

        assertThat(sideMenuItem2.getPricesForSize().get(size), equalTo(new BigDecimal(price2)));

    }

}