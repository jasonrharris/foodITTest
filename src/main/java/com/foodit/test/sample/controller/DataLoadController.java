package com.foodit.test.sample.controller;

import com.foodit.test.sample.entities.RestaurantData;
import com.foodit.test.sample.service.RestaurantDataService;
import com.foodit.test.sample.service.RestaurantDataServiceImpl;
import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.threewks.thundr.http.ContentType;
import com.threewks.thundr.http.HttpSupport;
import com.threewks.thundr.logger.Logger;
import com.threewks.thundr.view.jsp.JspView;
import com.threewks.thundr.view.string.StringView;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DataLoadController {
    private final RestaurantDataService restaurantDataService;

    public DataLoadController(RestaurantDataService restaurantDataService) {
        this.restaurantDataService = restaurantDataService;
    }

    public JspView instructions() {
		return new JspView("instructions.jsp");
	}

	public StringView load() {
		Logger.info("Loading data");
		List<String> restaurants = Lists.newArrayList("bbqgrill", "butlersthaicafe", "jashanexquisiteindianfood", "newchinaexpress");

        restaurantDataService.loadData(restaurants);

		return new StringView("Data loaded.");
	}

	public void viewData(String restaurant, HttpServletResponse response) throws IOException {
        String data =restaurantDataService.getRestaurantDataByName(restaurant).viewData();

        response.addHeader(HttpSupport.Header.ContentType, ContentType.ApplicationJson.value());
        response.getWriter().write(data);
		response.setContentLength(data.getBytes().length);
	}

}
