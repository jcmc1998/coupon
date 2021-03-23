package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.ItemDto;
import com.jmachillanda.coupon.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findItemsById(List<String> itemIds) {
        return itemRepository.findItemsById(removeDuplicateIds(itemIds));
    }

    private List<String> removeDuplicateIds(List<String> itemIds) {
        return itemIds
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
