package com.foodit.test.sample.controller;


import com.foodit.test.sample.entities.RestaurantData;
import com.googlecode.objectify.Key;
import com.threewks.thundr.http.ContentType;
import com.threewks.thundr.http.HttpSupport;
import com.threewks.thundr.view.json.JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Provides access to order data, by restaurant(s) or in total
 */
public class OrderReportController {
    public JsonView orderCountReport(String restaurantName, HttpServletResponse response) throws IOException {
            response.addHeader(HttpSupport.Header.ContentType, ContentType.ApplicationJson.value());
            RestaurantData restaurantLoadData = ofy().load().key(Key.create(RestaurantData.class, restaurantName)).now();
            String data = restaurantLoadData.viewData();
            response.getWriter().write(data);
            response.setContentLength(data.getBytes().length);

        return new JsonView(restaurantName);
    }
}
