package com.example.shopping.entity;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemname;

    @Column(nullable = false)
    private Long price;

    @Column(length = 1000)
    private String description;

    @ManyToOne(mappedBy = "wishlist")
    private List<Wishlist> wishlists;

    @Column(nullable = false)
    private Long quantity;
}