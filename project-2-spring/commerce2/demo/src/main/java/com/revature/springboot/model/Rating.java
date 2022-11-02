package com.revature.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name="ratings")
public @Data class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;

    private int customerId;

//    @ManyToOne
//    @JoinColumn(name="product_id")
    private int productId;

}
