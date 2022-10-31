package com.revature.springboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name="cart")
public @Data class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int userId;

    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    Product product;

    int quantity;

    // Empty
    public CartItem(){

    }

    // For adding to the cart
    public CartItem(int userId, Product product){
        this.userId = userId;
        this.product = product;
    }

    // For getting the cart
    public CartItem(int id, int userId, Product product){
        this.userId = userId;
        this.product = product;
    }

    // For getting the cart
    public CartItem(int id, int userId, Product product, int quantity){
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

}
