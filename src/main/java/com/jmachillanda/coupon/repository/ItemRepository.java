package com.jmachillanda.coupon.repository;

import com.jmachillanda.coupon.dto.ItemDto;
import static java.util.Arrays.asList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ItemRepository {

    @Autowired
    private RestTemplate restTemplate;

    public List<ItemDto> findItemsById(List<String> itemsId) {
        ItemDto firstItem = new ItemDto("MLA1", 100f);
        ItemDto secondItem = new ItemDto("MLA2", 200f);
        return asList(firstItem, secondItem);
    }
}
