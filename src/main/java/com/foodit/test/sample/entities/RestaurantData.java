package com.foodit.test.sample.entities;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class RestaurantData {

	@Id
	private String restaurant;

	private Text menuJson;
	private Text ordersJson;

	public RestaurantData(String restaurant, String menuJson, String ordersJson) {
		super();
		this.restaurant = restaurant;
		this.menuJson = new Text(menuJson);
		this.ordersJson = new Text(ordersJson);
	}

	public RestaurantData() {
        this("", "", "");
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public Text getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(Text menuJson) {
		this.menuJson = menuJson;
	}

	public Text getOrdersJson() {
		return ordersJson;
	}

	public void setOrdersJson(Text ordersJson) {
		this.ordersJson = ordersJson;
	}

	public String viewData() {

		return menuJson.getValue().concat(ordersJson.getValue());
	}
}
