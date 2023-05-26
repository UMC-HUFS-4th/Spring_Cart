package com.example.shopping.entity;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
    @JoinColumn (name = "wishlist_id")
    private WishList wishList;

    @Column(nullable = false)
    private Long quantity;
}