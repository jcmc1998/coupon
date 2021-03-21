package com.jmachillanda.coupon;

import com.jmachillanda.coupon.service.CouponService;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTests {

    @Autowired
    private CouponService couponService;

    @Test
    public void calculate_oneItemWithPriceBelowCouponAmount_returnsTheItem() {
        Map<String, Float> items = Map.ofEntries(entry("MLA1", 100.00f));
        Float amount = 120.00f;

        List<String> couponItems = couponService.calculate(items, amount);

        assertThat(couponItems).containsExactly("MLA1");
    }

    @Test
    public void calculate_fiveItemsWhosePriceIsBelowCouponAmount_returnsFiveItems() {
        Map<String, Float> items = Map
                .ofEntries(
                        entry("MLA1", 100.00f),
                        entry("MLA2", 210.00f),
                        entry("MLA3", 260.00f),
                        entry("MLA4", 80.00f),
                        entry("MLA5", 90.00f)
                );
        Float amount = 500.00f;

        List<String> couponItems = couponService.calculate(items, amount);

        assertThat(couponItems)
                .containsExactlyInAnyOrder("MLA1", "MLA2", "MLA4", "MLA5");
    }

    @Test
    public void calculate_twoItemsWhosePriceIsOverCouponAmount_returnsOneItem() {
        Map<String, Float> items = Map
                .ofEntries(
                        entry("MLA1", 100.00f),
                        entry("MLA2", 210.00f)
                );
        Float amount = 120.00f;

        List<String> couponItems = couponService.calculate(items, amount);

        assertThat(couponItems)
                .containsExactlyInAnyOrder("MLA1");
    }

}
