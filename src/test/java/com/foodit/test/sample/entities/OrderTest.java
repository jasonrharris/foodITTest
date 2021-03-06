package com.foodit.test.sample.entities;

import com.google.gson.Gson;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class OrderTest {

    @Test
    public void shouldUnmarshalOrderJSON(){
        int recVersion = 7;

        String json = "{\"recVersion\":" + recVersion + ",\"orderId\":5456722831343616,\"easyOrderNum\":1,\"created\":\"2014-03-23T19:06:37.459Z\",\"storeId\":\"bbqgrill\",\"storeName\":\"B-B-Q Grill\",\"totalValue\":9.50,\"lineItems\":[{\"id\":5,\"total\":\"7.50\",\"unitPrice\":\"7.50\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]},{\"id\":37,\"total\":\"2.00\",\"unitPrice\":\"2.00\",\"quantity\":1,\"promotion\":false,\"mealOptions\":[]}],\"status\":\"PaymentCaptured\",\"timeZoneId\":\"Europe/London\",\"collectionType\":\"Collection\",\"paymentType\":\"CREDIT_CARD\",\"addressDisplay\":\"1260 High Rd, London, N20 9HH\",\"contactNumber\":\"+442084467888\",\"domain\":\"www.bbqgrillwhetstone.co.uk\",\"currencyDisplay\":\"GBP\",\"commissionRate\":5,\"referenceNumber\":\"1\",\"receiptLogoUrl\":\"http://foodit-prod.appspot.com/api/v1/serveLogo/AMIfv94d6PQjKmDQU27RTz8vDRqEzeTwgsXXDKpNxfxcVJSGSI_8JFq_XHTbHV_ggLv2GFLFGXaAhekq0B2J0S9Vq3emJuJnB6KgQE2rUvaawdxjng6V5sDPg6brOktvZAsW22q23vuolFCOwsu54kIsAHDI7OpUmw/\"}";

        Gson gson = new Gson();

        Order order = gson.fromJson(json, Order.class);

        assertThat(order.getRecVersion(), equalTo(recVersion));

        assertThat(order.getLineItems().size(), equalTo(2) );


    }

}