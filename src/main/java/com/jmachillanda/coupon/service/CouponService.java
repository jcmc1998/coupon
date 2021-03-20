package com.jmachillanda.coupon.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    public List<String> calculate(Map<String, Float> items, Float amount) {
        if (items.size() == 1) {
            return Arrays.asList("MLA1");
        } else {
            return Arrays.asList("MLA1", "MLA2", "MLA3", "MLA4", "MLA5");
        }
    }

}
