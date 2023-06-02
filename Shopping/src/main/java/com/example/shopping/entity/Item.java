package com.example.shopping.entity;

import lombok.*;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String item_name;

    @Column(nullable = false)
    private Long price;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id", nullable = true)
    private WishList wishList;

    @Column(nullable = false)
    private Long quantity;

    public Long get_item_id() {
        return id;
    }

    public String get_item_name() {
        return item_name;
    }

    public void set_item_name(String item_name) {
        this.item_name = item_name;
    }

    public Long get_price() {
        return price;
    }

    public void set_price(Long price) {
        this.price = price;
    }

    public String get_description() {
        return description;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Long get_quantity() {
        return quantity;
    }

    public void set_quantity(Long quantity) {
        this.quantity = quantity;
    }
}