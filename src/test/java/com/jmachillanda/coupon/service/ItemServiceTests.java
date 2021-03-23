package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.ItemDto;
import com.jmachillanda.coupon.repository.ItemRepository;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ItemServiceTests {

    @Autowired
    private ItemService itemService;
    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void findItemsById_twoItemIds_returnsTwoItems() {
        ItemDto firstItem = new ItemDto("MLA1", 100f);
        ItemDto secondItem = new ItemDto("MLA2", 200f);
        Set<String> itemIds = new HashSet<>(asList(firstItem.getId(), secondItem.getId()));
        when(itemRepository.findItemsById(itemIds)).thenReturn(asList(firstItem, secondItem));

        List<ItemDto> items = itemService.findItemsByIds(itemIds);

        assertThat(items).containsExactly(firstItem, secondItem);
    }

    @Test
    public void findItemsById_zeroItemIds_returnsEmptyList() {
        Set<String> itemIds = Collections.EMPTY_SET;
        when(itemRepository.findItemsById(itemIds)).thenReturn(Collections.EMPTY_LIST);

        List<ItemDto> items = itemService.findItemsByIds(itemIds);

        assertThat(items).isEmpty();
    }

    @Test
    public void findItemsById_duplicateItemIds_returnsSingleItem() {
        ItemDto firstItem = new ItemDto("MLA1", 100f);
        Set<String> itemIds = new HashSet<>(asList(firstItem.getId()));
        Set<String> repeatedItemIds = new HashSet<>(asList(firstItem.getId(), firstItem.getId()));
        when(itemRepository.findItemsById(itemIds)).thenReturn(asList(firstItem));

        List<ItemDto> items = itemService.findItemsByIds(repeatedItemIds);

        assertThat(items).containsExactly(firstItem);
    }

}
