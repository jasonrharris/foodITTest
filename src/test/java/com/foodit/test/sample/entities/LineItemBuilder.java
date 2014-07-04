package com.foodit.test.sample.entities;

import java.math.BigDecimal;

public class LineItemBuilder {
    private int id;
    private BigDecimal total;
    private BigDecimal unitPrice;
    private int quantity;

    public LineItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public LineItemBuilder setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public LineItemBuilder setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public LineItemBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public LineItem createLineItem() {
        return new LineItem(id, total, unitPrice, quantity);
    }
}