package com.jmachillanda.coupon.dto;

import java.util.List;

public class CouponRecommendationDto {

    private List<String> itemIds;
    private float total;

    public CouponRecommendationDto(List<String> itemIds, float total) {
        this.itemIds = itemIds;
        this.total = total;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "itemIds=" + itemIds.toString() + ", total=" + total;
    }

}
