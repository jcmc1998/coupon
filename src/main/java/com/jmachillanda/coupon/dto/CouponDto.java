package com.jmachillanda.coupon.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CouponDto {

    @NotEmpty(message = "Item list cannot be null or empty")
    private final Set<String> itemIds;
    @Positive(message = "Coupon amount must be greater than zero")
    private final float amount;

    public CouponDto(Set<String> itemIds, float amount) {
        this.itemIds = itemIds;
        this.amount = amount;
    }

    public Set<String> getItemIds() {
        return itemIds;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "itemIds=" + itemIds.toString() + ", amount=" + amount;
    }

}
