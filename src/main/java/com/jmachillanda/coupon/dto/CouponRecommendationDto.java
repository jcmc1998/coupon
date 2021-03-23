package com.jmachillanda.coupon.dto;

import java.util.List;

public class CouponRecommendationDto {

    private final List<String> itemIds;
    private final float total;

    public CouponRecommendationDto(List<String> itemIds, float total) {
        this.itemIds = itemIds;
        this.total = total;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "itemIds=" + itemIds.toString() + ", total=" + total;
    }

}
