package com.foodit.test.sample.entities;

/**
 * A key used for looking up items within a particular restaurant
 */
public final class RestaurantItemKey {
    private final String storeId;
    private final int id;

    public RestaurantItemKey(String storeId, int id) {
        this.storeId = storeId;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantItemKey that = (RestaurantItemKey) o;

        if (id != that.id) return false;
        if (!storeId.equals(that.storeId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId.hashCode();
        result = 31 * result + id;
        return result;
    }
}
