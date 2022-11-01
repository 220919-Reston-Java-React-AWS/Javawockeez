package com.revature.springboot.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name="order_history")
public @Data class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int userId;

    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    Product product;

    int quantity;

    @Column(name = "purchase_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date purchaseDate;

    public Order(){

    }

    public Order(int userId, Product product, int quantity){
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

}
