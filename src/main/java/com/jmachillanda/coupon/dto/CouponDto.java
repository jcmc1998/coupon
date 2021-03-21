package com.jmachillanda.coupon.dto;

import java.util.ArrayList;
import java.util.List;

public class CouponDto {

    private float total;
    private List<String> itemIds;

    public CouponDto() {
        this(0f, new ArrayList<>());
    }

    public CouponDto(float total, List<String> itemIds) {
        this.total = total;
        this.itemIds = itemIds;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

}
