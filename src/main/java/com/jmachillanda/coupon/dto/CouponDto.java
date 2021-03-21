package com.jmachillanda.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class CouponDto {

    private List<String> itemIds;
    private float total;

    public CouponDto() {
        this(0f, new ArrayList<>());
    }

    public CouponDto(float total, List<String> itemIds) {
        this.total = total;
        this.itemIds = itemIds;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    @JsonProperty("item_ids")
    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    @JsonProperty("total")
    public float getTotal() {
        return total;
    }

    @JsonProperty("amount")
    public void setTotal(float total) {
        this.total = total;
    }

}
