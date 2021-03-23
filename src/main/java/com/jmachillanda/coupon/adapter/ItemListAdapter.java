package com.jmachillanda.coupon.adapter;

import com.jmachillanda.coupon.dto.ItemDto;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import org.springframework.stereotype.Component;

@Component
public class ItemListAdapter {

    public Map<String, Float> getItemIdsAndPrices(List<ItemDto> items) {
        return items.stream()
                .collect(toMap(ItemDto::getId, ItemDto::getPrice));
    }

}
