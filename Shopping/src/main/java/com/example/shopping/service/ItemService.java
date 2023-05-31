package com.example.shopping.service;

import com.example.shopping.entity.Item;
import com.example.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class ItemService {

    private final ItemRepository itemRepository;

    public String createItem(String item_name, Long price, String description, Long quantity) {

        Item item = Item.builder().item_name(item_name).price(price).description(description).quantity(quantity).build();
        itemRepository.save(item);
        return item_name;
    }

    public List<Item> getItems() {

        return itemRepository.findAll();

    }

    public String updateItem(Long item_id, String item_name, Long price, String description, Long quantity) {

        Optional<Item> item = itemRepository.findById(item_id);

        if(item.isEmpty()) {
            throw new RuntimeException(item_id + "에 해당하는 아이템이 존재하지 않습니다.");
        }

        Item foundItem = item.get();
        foundItem.setItem_name(item_name);
        foundItem.setPrice(price);
        foundItem.setDescription(description);
        foundItem.setQuantity(quantity);
        itemRepository.save(foundItem);
        return foundItem.getItem_name();

    }

    public Item delete(Long item_id) {
        Optional<Item> item = itemRepository.findById(item_id);

        if(!item.isPresent()) {
            throw new RuntimeException(item_id + "에 해당하는 상품이 존재하지 않습니다");
        }

        Item founditem = item.get();
        itemRepository.delete(founditem);
        return founditem;
    }

}
