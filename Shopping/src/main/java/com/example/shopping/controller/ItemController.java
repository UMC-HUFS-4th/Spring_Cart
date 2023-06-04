package com.example.shopping.controller;

import com.example.shopping.entity.Item;
import com.example.shopping.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/shopping/item") //http://localhost::8080/shopping/item
@RestController

public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createItem(
            @RequestBody Item item) {
        itemService.createItem(item);
        return ResponseEntity.ok("상품이 등록되었습니다: ");
    }

    @GetMapping("/get/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable Long itemId) {
        Item item = itemService.getItem(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<String> updateItem(
            @PathVariable Long itemId,
            @RequestBody Item item
    ) {
        String updateItem = itemService.updateItem(itemId, item);
        return ResponseEntity.ok(updateItem + " 상품이 수정되었습니다.");
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<String> deleteItem(
            @PathVariable Long itemId
    ) {
        String deletedItemName = itemService.deleteItem(itemId);
        return ResponseEntity.ok(deletedItemName + " 상품이 삭제되었습니다.");
    }

}
