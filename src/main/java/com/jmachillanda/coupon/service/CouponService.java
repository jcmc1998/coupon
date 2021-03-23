package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.adapter.ItemListAdapter;
import com.jmachillanda.coupon.dto.CouponDto;
import com.jmachillanda.coupon.dto.CouponRecommendationDto;
import com.jmachillanda.coupon.dto.ItemDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final ItemService itemService;
    private final ItemListAdapter itemListAdapter;

    public CouponService(ItemService itemService, ItemListAdapter itemListAdapter) {
        this.itemService = itemService;
        this.itemListAdapter = itemListAdapter;
    }

    public CouponRecommendationDto getRecommendation(CouponDto couponDto) {
        List<ItemDto> items = itemService.findItemsByIds(couponDto.getItemIds());
        List<ItemDto> itemsWithLowerPrice = getItemsWithLowerPriceThanAmount(items, couponDto.getAmount());
        Map<String, Float> itemsMap = itemListAdapter.getItemIdsAndPrices(itemsWithLowerPrice);

        return calculate(itemsMap, couponDto.getAmount());
    }

    public CouponRecommendationDto calculate(Map<String, Float> items, Float amount) {
        TreeMap<String, Float> itemsCopy = new TreeMap<>(items);
        List<String> recommendedItemIds = new ArrayList<>();
        Float recommendedAmount = 0f;

        for (Map.Entry<String, Float> actualItem : items.entrySet()) {
            String actualItemId = actualItem.getKey();
            Float actualItemPrice = actualItem.getValue();

            List<String> possibleItemIds = new ArrayList<>();
            possibleItemIds.add(actualItemId);
            Float possibleAmount = actualItemPrice;

            if (actualItemPrice.equals(amount)) {
                return new CouponRecommendationDto(Arrays.asList(actualItemId), amount);
            }

            for (Map.Entry<String, Float> itemCopy : itemsCopy.entrySet()) {
                String itemCopyId = itemCopy.getKey();
                Float itemCopyPrice = itemCopy.getValue();

                if (actualItemId.equals(itemCopyId)) {
                    continue;
                }

                Float sum = possibleAmount + itemCopyPrice;

                if (sum <= amount) {
                    possibleItemIds.add(itemCopyId);
                    possibleAmount = sum;
                }
            }
            itemsCopy.pollLastEntry();

            if (possibleAmount > recommendedAmount) {
                recommendedAmount = possibleAmount;
                recommendedItemIds = possibleItemIds;
            }
        }
        return new CouponRecommendationDto(recommendedItemIds, recommendedAmount);
    }

    private List<ItemDto> getItemsWithLowerPriceThanAmount(List<ItemDto> items, Float amount) {
        List<ItemDto> itemsWithLowerPriceThanAmount = items
                .stream()
                .filter(item -> item.getPrice() <= amount)
                .collect(Collectors.toList());

        if (itemsWithLowerPriceThanAmount.isEmpty()) {
            throw new NoSuchElementException("Insufficient amount to buy at least one item");
        } else {
            return itemsWithLowerPriceThanAmount;
        }
    }

//    Nivel 1
//    public List<String> calculate(Map<String, Float> items, Float amount) {
//        Map<String, Float> validItems = getItemsWithLowerPriceThanAmount(items, amount);
//        TreeMap<String, Float> itemsCopy = new TreeMap<>(items);
//        List<String> recommendedItemIds = new ArrayList<>();
//        Float optimalAmount = 0f;
//
//        for (Map.Entry<String, Float> actualItem : validItems.entrySet()) {
//            String actualItemId = actualItem.getKey();
//            Float actualItemPrice = actualItem.getValue();
//
//            List<String> possibleItemIds = new ArrayList<>();
//            possibleItemIds.add(actualItemId);
//            Float possibleAmount = actualItemPrice;
//
//            if (actualItemPrice.equals(amount)) {
//                return Arrays.asList(actualItemId);
//            }
//
//            for (Map.Entry<String, Float> itemCopy : itemsCopy.entrySet()) {
//                String itemCopyId = itemCopy.getKey();
//                Float itemCopyPrice = itemCopy.getValue();
//
//                if (actualItemId.equals(itemCopyId)) {
//                    continue;
//                }
//
//                Float sum = possibleAmount + itemCopyPrice;
//
//                if (sum <= amount) {
//                    possibleItemIds.add(itemCopyId);
//                    possibleAmount = sum;
//                }
//            }
//            itemsCopy.pollLastEntry();
//
//            if (possibleAmount > optimalAmount) {
//                optimalAmount = possibleAmount;
//                recommendedItemIds = possibleItemIds;
//            }
//        }
//        return recommendedItemIds;
//    }
}
