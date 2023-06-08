package com.example.shopping.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlist_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = true)
    private Long total_price;

    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    private List<Item> items;

    public Long get_wishlist_id() {
        return wishlist_id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user.getWishList() != this) {
            user.setWishList(this);
        }
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Long getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Long total_price) {
        this.total_price = total_price;
    }
}
