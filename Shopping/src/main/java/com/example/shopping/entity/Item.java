package com.example.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "price")
    private Long price;

    @Column(length = 1000, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id", nullable = true)
    private WishList wishList;

    @Column(name = "quantity", columnDefinition = "bigint default 1")
    private Long quantity;

    public Long getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}