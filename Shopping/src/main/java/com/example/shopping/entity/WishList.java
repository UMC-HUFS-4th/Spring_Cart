package com.example.shopping.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WishList {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long wishlist_id;

    @OneToOne
    @JoinColumn ( name = "user_id" )
    private User user;

    @Column (nullable = false)
    private Long total_price;
}