package com.foodit.test.sample.entities;

import java.math.BigDecimal;

/**
 * Detail of each item ordered
 */
public class LineItem {

    private int id;

    private BigDecimal total;

    private BigDecimal unitPrice;

    private int quantity;

    public LineItem(int id, BigDecimal total, BigDecimal unitPrice, int quantity) {
        this.id = id;
        this.total = total;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public LineItem() {
    }

    public int getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}

