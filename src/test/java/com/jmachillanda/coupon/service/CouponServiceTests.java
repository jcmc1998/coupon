package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.CouponDto;
import com.jmachillanda.coupon.dto.CouponRecommendationDto;
import com.jmachillanda.coupon.dto.ItemDto;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    private ItemService itemService;

    @Test
    public void getRecommendation_oneItemWithPriceBelowCouponAmount_returnsTheItem() {
        ArrayList<String> itemIds = new ArrayList<>();
        itemIds.add("MLA1");
        Float amount = 120.00f;
        CouponDto couponDto = new CouponDto(itemIds, amount);
        ItemDto item = new ItemDto("MLA1", 100f);
        when(itemService.findItemsById(itemIds)).thenReturn(asList(item));

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
        when(itemService.findItemsById(itemIds)).thenReturn(asList(itemOne, itemTwo));

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
        when(itemService.findItemsById(itemIds)).thenReturn(asList(itemOne, itemTwo));

        CouponRecommendationDto recommendedCoupon = couponService.getRecommendation(couponDto);

        assertThat(recommendedCoupon.getItemIds()).containsExactlyInAnyOrder("MLA1", "MLA2");
        assertThat(recommendedCoupon.getTotal()).isEqualTo(200.22f);
    }

    @Test
    public void getRecommendation_itemRepeatedTwice_returnsASingleItem() {
        ArrayList<String> itemIds = new ArrayList<>();
        itemIds.add("MLA1");
        itemIds.add("MLA1");
        Float amount = 200f;
        CouponDto couponDto = new CouponDto(itemIds, amount);
        ItemDto itemOne = new ItemDto("MLA1", 100f);
        when(itemService.findItemsById(itemIds)).thenReturn(asList(itemOne));

        CouponRecommendationDto recommendedCoupon = couponService.getRecommendation(couponDto);

        assertThat(recommendedCoupon.getItemIds()).containsExactlyInAnyOrder("MLA1");
        assertThat(recommendedCoupon.getTotal()).isEqualTo(100f);
    }

    @Test
    public void getRecommendation_amountInsufficientToBuyAtLeastOneItem_throwsException() {
        ArrayList<String> itemIds = new ArrayList<>();
        itemIds.add("MLA1");
        Float insufficientAmount = 0f;
        CouponDto couponDto = new CouponDto(itemIds, insufficientAmount);
        ItemDto itemOne = new ItemDto("MLA1", 100f);
        when(itemService.findItemsById(itemIds)).thenReturn(asList(itemOne));

        assertThatThrownBy(() -> couponService.getRecommendation(couponDto))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Insufficient amount to buy at least one item");
    }

}
