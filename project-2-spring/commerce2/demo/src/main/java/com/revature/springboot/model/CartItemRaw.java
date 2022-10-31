package com.revature.springboot.model;

import lombok.Data;

public @Data class CartItemRaw {

    //int id;
    int userId;
    int productId;
    int quantity;

}
