package com.jmachillanda.coupon.repository;

import com.jmachillanda.coupon.dto.ItemDto;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class ItemRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mercado-libre-host}")
    private String mercadoLibreHost;

    public List<ItemDto> findItemsById(List<String> itemsId) {
        return itemsId
                .stream()
                .map(itemId -> restTemplate.getForObject(getUriForItemId(itemId), ItemDto.class))
                .collect(Collectors.toList());
    }

    private URI getUriForItemId(String itemId) {
        return UriComponentsBuilder.fromUriString(mercadoLibreHost)
                .pathSegment("/items/")
                .pathSegment(itemId)
                .build()
                .toUri();
    }

}
