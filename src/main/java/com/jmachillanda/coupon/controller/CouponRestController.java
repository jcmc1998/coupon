package com.jmachillanda.coupon.controller;

import com.jmachillanda.coupon.dto.CouponDto;
import com.jmachillanda.coupon.dto.CouponRecommendationDto;
import com.jmachillanda.coupon.service.CouponService;
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

    @PostMapping()
    public CouponRecommendationDto getRecommendation(@RequestBody CouponDto couponDto) {
        return couponService.getRecommendation(couponDto);
    }

}
