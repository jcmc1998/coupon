package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.CouponDto;
import com.jmachillanda.coupon.dto.CouponRecommendationDto;
import com.jmachillanda.coupon.dto.ItemDto;
import com.jmachillanda.coupon.repository.ItemRepository;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CouponServiceTests {

    @Autowired
    private CouponService couponService;
    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void getRecommendation_oneItemWithPriceBelowCouponAmount_returnsTheItem() {
        ArrayList<String> itemIds = new ArrayList<>();
        itemIds.add("MLA1");
        Float amount = 120.00f;
        CouponDto couponDto = new CouponDto(itemIds, amount);
        ItemDto item = new ItemDto("MLA1", 100f);
        when(itemRepository.findItemsById(itemIds)).thenReturn(asList(item));

        CouponRecommendationDto recommendedCoupon = couponService.getRecommendation(couponDto);

        assertThat(recommendedCoupon.getItemIds()).containsExactly("MLA1");
        assertThat(recommendedCoupon.getTotal()).isEqualTo(100.00f);
    }

    @Test
    public void getRecommendation_twoItemsWhosePriceIsOverCouponAmount_returnsOneItem() {
        ArrayList<String> itemIds = new ArrayList<>();
        itemIds.add("MLA1");
        itemIds.add("MLA2");
        Float amount = 120.00f;
        CouponDto couponDto = new CouponDto(itemIds, amount);
        ItemDto itemOne = new ItemDto("MLA1", 100.00f);
        ItemDto itemTwo = new ItemDto("MLA2", 210.00f);
        when(itemRepository.findItemsById(itemIds)).thenReturn(asList(itemOne, itemTwo));

        CouponRecommendationDto recommendedCoupon = couponService.getRecommendation(couponDto);

        assertThat(recommendedCoupon.getItemIds()).containsExactlyInAnyOrder("MLA1");
        assertThat(recommendedCoupon.getTotal()).isEqualTo(100.00f);
    }

    @Test
    public void getRecommendation_twoItemsWithDecimalPricesEqualToAmount_returnsBothItems() {
        ArrayList<String> itemIds = new ArrayList<>();
        itemIds.add("MLA1");
        itemIds.add("MLA2");
        Float amount = 200.22f;
        CouponDto couponDto = new CouponDto(itemIds, amount);
        ItemDto itemOne = new ItemDto("MLA1", 100.11f);
        ItemDto itemTwo = new ItemDto("MLA2", 100.11f);
        when(itemRepository.findItemsById(itemIds)).thenReturn(asList(itemOne, itemTwo));

        CouponRecommendationDto recommendedCoupon = couponService.getRecommendation(couponDto);

        assertThat(recommendedCoupon.getItemIds()).containsExactlyInAnyOrder("MLA1", "MLA2");
        assertThat(recommendedCoupon.getTotal()).isEqualTo(200.22f);
    }

}
