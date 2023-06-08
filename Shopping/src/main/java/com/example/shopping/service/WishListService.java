package com.example.shopping.service;

import com.example.shopping.entity.Item;
import com.example.shopping.entity.User;
import com.example.shopping.entity.WishList;
import com.example.shopping.repository.ItemRepository;
import com.example.shopping.repository.UserRepository;
import com.example.shopping.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishListService {
    private final WishListRepository wishListRepository;
    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    @Autowired
    public WishListService(UserRepository userRepository, WishListRepository wishListRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.itemRepository = itemRepository;

    }

    public WishList createWishList(User user) {
        WishList wishList = new WishList();
        wishList.setUser(user);
        return wishListRepository.save(wishList);
    }

    public Item addToWishList(Long wishlist_id, Long item_id) {
        //wishlist_id로 wishlist가 존재하는지 조회
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);
        Optional<Item> itemOptional = itemRepository.findById(item_id);

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

        // 변경 사항을 저장
        wishListRepository.save(wishList);
        return itemRepository.save(newItem);
    }

    public List<Item> getWishList(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        WishList wishList = wishListRepository.findByUser(user);
        if (wishList == null) {
            throw new RuntimeException("위시리스트가 존재하지 않습니다.");
        }
        return wishList.getItems();
    }

    public void setItemQuantity(Long wishlist_id, Long item_id, Item itemDetails) {
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);

        if (wishListOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 위시리스트입니다.");
        }

        WishList wishList = wishListOptional.get();
        Optional<Item> itemOptional = wishList.getItems().stream()
                .filter(item -> item.getItem_id().equals(item_id))
                .findFirst();
        if (itemOptional.isEmpty()) {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        Item item = itemOptional.get();
        if (itemDetails.getQuantity() != null) {
            item.setQuantity(itemDetails.getQuantity());
        }

        wishListRepository.save(wishList);
    }

    public void updateTotalPrice(Long wishlist_id) {
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);

        if (wishListOptional.isPresent()) {
            WishList wishList = wishListOptional.get();
            List<Item> itemList = wishList.getItems();

            // 총 가격 초기화
            Long totalPrice = 0L;

            // 각 아이템의 가격과 수량을 곱하여 총 가격 계산
            for (Item item : itemList) {
                totalPrice += item.getPrice() * item.getQuantity();
            }

            wishList.setTotal_price(totalPrice);
            wishListRepository.save(wishList);
        } else {
            throw new RuntimeException("존재하지 않는 위시리스트입니다.");
        }
    }

    public void deleteFromWishList(Long wishlist_id, Long item_id) {
        Optional<WishList> wishListOptional = wishListRepository.findById(wishlist_id);
        Optional<Item> itemOptional = itemRepository.findById(item_id);

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

        item.setWishList(null); // 상품의 위시리스트 참조를 제거

        // 변경 사항을 저장
        wishListRepository.save(wishList);
        itemRepository.save(item);
    }
}
