package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.ItemDto;
import com.jmachillanda.coupon.repository.ItemRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Map<String, Float> getItemPrices(List<String> itemIds) {
        List<ItemDto> items = itemRepository.findItemsById(itemIds);
        return convertItemListToMap(items);
    }

    private Map<String, Float> convertItemListToMap(List<ItemDto> items) {
        return items.stream()
                .collect(Collectors.toMap(ItemDto::getId, ItemDto::getPrice));
    }
}
