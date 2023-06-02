package com.example.shopping.service;

import com.example.shopping.entity.Item;
import com.example.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public String createItem(Item item) {
        itemRepository.save(item);
        return item.get_item_name();
    }

    public Item getItem(Long item_id) {
        Optional<Item> item = itemRepository.findById(item_id);
        if(item.isPresent()) {
            return item.get();
        } else {throw new RuntimeException("존재하지 않는 상품입니다.");
        }
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public String updateItem(Long item_id, Item itemDetails) {
        Optional<Item> itemOptional = itemRepository.findById(item_id);

        if (itemOptional.isEmpty()) {
            throw new RuntimeException(item_id + "에 해당하는 아이템이 존재하지 않습니다.");
        }
        Item item = itemOptional.get();

        if (itemDetails.get_item_name() != null) {
            item.set_item_name(itemDetails.get_item_name());
        }
        if (itemDetails.get_price() != null) {
            item.set_price(itemDetails.get_price());
        }
        if (itemDetails.get_description() != null) {
            item.set_description(itemDetails.get_description());
        }
        if (itemDetails.get_quantity() != null) {
            item.set_quantity(itemDetails.get_quantity());
        }

        return item.get_item_name();
    }

    public void delete(Long item_id) {
        Optional<Item> item = itemRepository.findById(item_id);

        if(!item.isPresent()) {
            throw new RuntimeException(item_id + "에 해당하는 상품이 존재하지 않습니다");
        }

        Item founditem = item.get();
        itemRepository.delete(founditem);
    }
}
