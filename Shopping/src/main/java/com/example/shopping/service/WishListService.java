package com.example.shopping.service;

import com.example.shopping.entity.Item;
import com.example.shopping.entity.User;
import com.example.shopping.entity.WishList;
import com.example.shopping.repository.ItemRepository;
import com.example.shopping.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository, ItemRepository itemRepository) {
        this.wishListRepository = wishListRepository;
        this.itemRepository = itemRepository;

    }

    public String createWishList(User user) {

        WishList wishList = new WishList();
        wishList.setUser(user);
        wishListRepository.save(wishList);
        return "위시리스트가 생성되었습니다.";
    }

    public String addToWishList(Long wishlist_id, Item item) {
        //wishlist_id로 wishlist가 존재하는지 조회
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);
        Optional<Item> itemOptional = itemRepository.findById(item.getItem_id());

        if (wishListOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 위시리스트입니다.");
        }

        if (itemOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        WishList wishList = wishListOptional.get();
        Item newItem = itemOptional.get();

        List<Item> itemList = wishList.getItems();
        itemList.add(newItem);

        newItem.setWishList(wishList);
        return "상품을 위시리스트에 추가하였습니다.";
    }

    public List<Item> getWishList(Long wishlist_id) {
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);

        if (wishListOptional.isPresent()) {
            WishList wishList = wishListOptional.get();
            List<Item> itemList = wishList.getItems();
            Long total_price = 0L;

            System.out.println("Item List:");
            for (Item item : itemList) {
                System.out.println("- " + item.getItem_name());
                total_price += item.getPrice();
            }

            System.out.println("Total Price: " + total_price);
            return itemList;
        } else {
            throw new RuntimeException("존재하지 않는 위시리스트입니다.");
        }
    }

    public String deleteFromWishList(Long wishlist_id, Item items) {
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);
        Optional<Item> itemOptional = itemRepository.findById(items.getItem_id());

        if (wishListOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 위시리스트입니다.");
        }

        if (itemOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 아이템입니다.");
        }

        WishList wishList = wishListOptional.get();
        Item item = itemOptional.get();

        if (!wishList.getItems().contains(item)) {
            throw new RuntimeException("위시리스트에 저장되지 않은 상품입니다.");
        }

        List<Item> itemList = wishList.getItems();
        itemList.remove(item);
        return "상품이 제거되었습니다.";
    }
}
