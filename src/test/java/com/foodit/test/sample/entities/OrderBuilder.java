package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.List;

public class OrderBuilder {
    private int recVersion;
    private long orderId;
    private int easyOrderNum;
    private String created;
    private String storeId;
    private BigDecimal totalValue;
    private List<LineItem> lineItems;
    private String status;
    private String timeZoneId;

    public OrderBuilder setRecVersion(int recVersion) {
        this.recVersion = recVersion;
        return this;
    }

    public OrderBuilder setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderBuilder setEasyOrderNum(int easyOrderNum) {
        this.easyOrderNum = easyOrderNum;
        return this;
    }

    public OrderBuilder setCreated(String created) {
        this.created = created;
        return this;
    }

    public OrderBuilder setStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public OrderBuilder setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public OrderBuilder setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
        return this;
    }

    public Order createOrder() {
        return new Order(recVersion, orderId, easyOrderNum, created, storeId, totalValue, lineItems, status, timeZoneId);
    }
}