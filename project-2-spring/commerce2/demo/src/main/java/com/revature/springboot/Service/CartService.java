package com.revature.springboot.Service;

import com.revature.springboot.Repository.CartRepo;
import com.revature.springboot.Repository.ProductRepo;
import com.revature.springboot.Repository.UserRepo;
import com.revature.springboot.model.CartItem;
import com.revature.springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartService {

    @Autowired
    CartRepo cr;

    @Autowired
    ProductRepo pr;

//    @Autowired
//    UserRepo ur;

    public List<Product> getCart(int userId){
        List<CartItem> userCart = cr.findByUserId(userId);

        List<Product> products = new ArrayList<Product>();
        for (CartItem item: userCart){
            products.add( item.getProduct() );
        }

        return products;
    }

    public void addToCart(int userId, int prodId){
        Product product = pr.findById(prodId).get(0);

        CartItem newItem = new CartItem(userId, product);

        cr.save(newItem);
    }




}
