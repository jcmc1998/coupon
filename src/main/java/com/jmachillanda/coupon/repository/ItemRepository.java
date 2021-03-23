package com.jmachillanda.coupon.repository;

import com.jmachillanda.coupon.dto.ItemDto;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class ItemRepository {

    private final RestTemplate restTemplate;

    public ItemRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${mercado-libre-host}")
    private String mercadoLibreHost;

    @Cacheable(value = "items")
    public List<ItemDto> findItemsById(Set<String> itemsId) {
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
