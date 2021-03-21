package com.jmachillanda.coupon.controller;

import com.jmachillanda.coupon.dto.CouponDto;
import com.jmachillanda.coupon.service.CouponService;
import com.jmachillanda.coupon.service.ItemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
public class CouponRestController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public CouponDto getRecommendation(@RequestBody CouponDto couponDto) {
        Map<String, Float> itemPrices = itemService.getItemPrices(couponDto.getItemIds());
        List<String> itemIds = couponService.calculate(itemPrices, couponDto.getTotal());

        return new CouponDto(0f, itemIds);
    }

}
