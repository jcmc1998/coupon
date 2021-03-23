package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.ItemDto;
import com.jmachillanda.coupon.repository.ItemRepository;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findItemsByIds(Set<String> itemIds) {
        return itemRepository.findItemsById(itemIds);
    }

}
