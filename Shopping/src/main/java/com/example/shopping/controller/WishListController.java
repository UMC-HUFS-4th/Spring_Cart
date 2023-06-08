package com.example.shopping.controller;

import com.example.shopping.entity.Item;
import com.example.shopping.entity.User;
import com.example.shopping.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/shopping/wishlist")
@RestController
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createWishList(
            @RequestBody User user
    ) {
        wishListService.createWishList(user);
        return ResponseEntity.ok("위시리스트가 연결되었습니다.");
    }

    @PostMapping("/{wishlist_id}/add/{item_id}")
    public ResponseEntity<String> addToWishList(
            @PathVariable Long wishlist_id,
            @PathVariable Long item_id
    ) {
        Item item = wishListService.addToWishList(wishlist_id, item_id);
        return ResponseEntity.ok(item.getItem_name() + " 상품이 위시리스트에 추가되었습니다.");
    }

    @GetMapping("/get/{user_id}")
    public ResponseEntity<List<Item>> getWishList(@PathVariable Long user_id) {
        List<Item> itemList = this.wishListService.getWishList(user_id);
        return ResponseEntity.ok().body(itemList);
    }

    @PutMapping("/{wishlist_id}/moderate/{item_id}/quantity")
    public ResponseEntity<String> setItemQuantity(
            @PathVariable Long wishlist_id,
            @PathVariable Long item_id,
            @RequestBody Item item
    ) {
        wishListService.setItemQuantity(wishlist_id, item_id, item);

        return ResponseEntity.ok(" 상품의 수량이 조정되었습니다.");
    }

    @DeleteMapping("/{wishlist_id}/delete/{item_id}")
    public ResponseEntity<String> deleteFromWishList(
            @PathVariable Long wishlist_id,
            @PathVariable Long item_id
    ) {
        wishListService.deleteFromWishList(wishlist_id, item_id);
        return ResponseEntity.ok("상품이 삭제되었습니다.");
    }


}
