package com.jmachillanda.coupon.service;

import com.jmachillanda.coupon.dto.ItemDto;
import com.jmachillanda.coupon.repository.ItemRepository;
import static java.util.Arrays.asList;
import java.util.List;
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
    public void getItemPrices_twoItems_returnsTwoItems() {
        ItemDto firstItem = new ItemDto("MLA1", 100f);
        ItemDto secondItem = new ItemDto("MLA2", 200f);
        List<String> itemIds = asList(firstItem.getId(), secondItem.getId());
        when(itemRepository.findItemsById(itemIds)).thenReturn(asList(firstItem, secondItem));

        List<ItemDto> items = itemService.findItemsById(itemIds);

        assertThat(items).containsExactly(firstItem, secondItem);
    }

}
