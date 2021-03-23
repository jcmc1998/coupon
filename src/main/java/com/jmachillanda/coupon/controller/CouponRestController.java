package com.jmachillanda.coupon.controller;

import com.jmachillanda.coupon.dto.CouponDto;
import com.jmachillanda.coupon.dto.CouponRecommendationDto;
import com.jmachillanda.coupon.service.CouponService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
public class CouponRestController {

    private static final Logger logger = LoggerFactory.getLogger(CouponRestController.class);

    private final CouponService couponService;

    public CouponRestController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping()
    public CouponRecommendationDto getRecommendation(@Valid @RequestBody CouponDto couponDto) {
        CouponRecommendationDto couponRecommendation = couponService.getRecommendation(couponDto);

        if (logger.isInfoEnabled()) {
            logger.info("POST /api/coupon | INPUT: " + couponDto);
            logger.info("POST /api/coupon | OUTPUT: " + couponRecommendation);
        }
        return couponRecommendation;
    }

}
