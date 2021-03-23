package com.jmachillanda.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CouponDto {

    private List<String> itemIds;
    private float amount;

    public CouponDto(List<String> itemIds, float amount) {
        this.itemIds = itemIds;
        this.amount = amount;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    @JsonProperty("item_ids")
    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "itemIds=" + itemIds.toString() + ", amount=" + amount;
    }

}
