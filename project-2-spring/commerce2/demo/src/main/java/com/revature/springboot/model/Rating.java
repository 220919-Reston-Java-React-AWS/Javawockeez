package com.revature.springboot.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="ratings")
public @Data class Rating {

    private int rating;
    private int customerId;

//    @ManyToOne
//    @JoinColumn(name="product_id")
    @Id
    private int productId;

}
