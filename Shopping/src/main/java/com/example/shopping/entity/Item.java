package com.example.shopping.entity;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @Column(nullable = false)
    private String item_name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long quantity;

}
