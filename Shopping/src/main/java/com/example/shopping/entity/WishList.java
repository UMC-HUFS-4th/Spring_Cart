package com.example.shopping.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class WishList {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long wishlist_id;

    @OneToOne
    @JoinColumn ( name = "user_id" )
    private User user;

    @Column (nullable = false)
    private Long total_price;

    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    private List<Item> items;

    public Long get_wishlist_id() {
        return wishlist_id;
    }

    public void setUser(User user){
        this.user = user;
        user.setWishList(this);
    }

    public List<Item> getItems() {
        return this.items;
    }
}
