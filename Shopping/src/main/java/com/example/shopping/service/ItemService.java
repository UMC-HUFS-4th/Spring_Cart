package com.example.shopping.service;

import com.example.shopping.entity.Item;
import com.example.shopping.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        Item newItem = Item.builder()
                .item_name(item.getItem_name())
                .price(item.getPrice())
                .description(item.getDescription())
                .quantity(item.getQuantity())
                .build();
        return itemRepository.save(newItem);
    }

    public Item getItem(Long item_id) {
        Optional<Item> item = itemRepository.findById(item_id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public String updateItem(Long item_id, Item itemDetails) {
        Optional<Item> itemOptional = itemRepository.findById(item_id);

        if (itemOptional.isEmpty()) {
            throw new RuntimeException(item_id + "에 해당하는 아이템이 존재하지 않습니다.");
        }
        Item item = itemOptional.get();

        if (itemDetails.getItem_name() != null) {
            item.setItem_name(itemDetails.getItem_name());
        }
        if (itemDetails.getPrice() != null) {
            item.setPrice(itemDetails.getPrice());
        }
        if (itemDetails.getDescription() != null) {
            item.setDescription(itemDetails.getDescription());
        }
        if (itemDetails.getQuantity() != null) {
            item.setQuantity(itemDetails.getQuantity());
        }

        return item.getItem_name();
    }

    public String deleteItem(Long item_id) {
        Optional<Item> itemOptional = itemRepository.findById(item_id);

        if (!itemOptional.isPresent()) {
            throw new RuntimeException(item_id + "에 해당하는 상품이 존재하지 않습니다");
        }

        Item foundItem = itemOptional.get();
        String itemName = foundItem.getItem_name();
        itemRepository.delete(foundItem);

        return itemName;
    }
}
