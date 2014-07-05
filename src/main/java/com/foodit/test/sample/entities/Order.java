package com.foodit.test.sample.entities;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.math.BigDecimal;
import java.util.List;

/**
 * Captures order placed data.
 */
public class Order {

    private int recVersion;
    private long orderId;
    private int easyOrderNum;
    private String created;
    private String storeId;

    private BigDecimal totalValue;

    private List<LineItem> lineItems;

    private String status;

    private String timeZoneId;

    public Order(int recVersion, long orderId, int easyOrderNum, String created, String storeId, BigDecimal totalValue, List<LineItem> lineItems, String status, String timeZoneId) {
        this.recVersion = recVersion;
        this.orderId = orderId;
        this.easyOrderNum = easyOrderNum;
        this.created = created;
        this.storeId = storeId;
        this.totalValue = totalValue;
        this.lineItems = lineItems;
        this.status = status;
        this.timeZoneId = timeZoneId;
    }

    public Order() {
    }

    public int getRecVersion() {
        return recVersion;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getEasyOrderNum() {
        return easyOrderNum;
    }

    public String getCreated() {
        return created;
    }

    public String getStoreId() {
        return storeId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public String getStatus() {
        return status;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }
}
