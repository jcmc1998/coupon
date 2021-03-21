package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.CouponDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    public List<String> calculate(Map<String, Float> items, Float amount) {
        ArrayList<String> itemIds = new ArrayList<>(items.keySet());
        ArrayList<Float> itemPrices = new ArrayList<>(items.values());

        CouponDto optimalCoupon = new CouponDto();

        Set<CouponDto> coupons = new HashSet<>();
        coupons.add(optimalCoupon);

        boolean foundTotalEqualToAmount = false;
        int itemPosition = 0;
        for (Float actualItemPrice : itemPrices) {
            Set<CouponDto> newCalculatedCoupons = new HashSet<>();

            if (foundTotalEqualToAmount) {
                break;
            }

            for (CouponDto coupon : coupons) {
                List<String> newCouponDto = new ArrayList<>(coupon.getItemIds());
                newCouponDto.add(itemIds.get(itemPosition));

                CouponDto newCoupon = new CouponDto(coupon.getTotal() + actualItemPrice, newCouponDto);

                if (newCoupon.getTotal() < amount) {
                    newCalculatedCoupons.add(newCoupon);

                    if (newCoupon.getTotal() == amount) {
                        foundTotalEqualToAmount = true;
                    }

                    if (newCoupon.getTotal() > optimalCoupon.getTotal()) {
                        optimalCoupon = newCoupon;
                    }
                }
            }
            coupons.addAll(newCalculatedCoupons);
            itemPosition++;
        }

        return optimalCoupon.getItemIds();
    }

}
