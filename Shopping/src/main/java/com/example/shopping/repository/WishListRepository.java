package com.example.shopping.repository;

import com.example.shopping.entity.User;
import com.example.shopping.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByUser(User user);
}
