package com.jmachillanda.coupon.dto;

public class ItemDto {

    private String id;
    private Float price;

    public ItemDto() {
    }

    public ItemDto(String id, Float price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Float getPrice() {
        return price;
    }

}
