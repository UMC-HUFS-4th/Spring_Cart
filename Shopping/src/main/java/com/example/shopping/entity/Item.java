package com.example.shopping.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

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
    @JoinColumn(name = "wishlist_id", nullable = true)
    private WishList wishlist_id;

    @Column(nullable = false)
    private Long quantity;
}