package com.jmachillanda.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class CouponDto {

    @NotEmpty(message = "Item list cannot be null or empty")
    private List<String> itemIds;
    @Positive(message = "Coupon amount must be greater than zero")
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
