package com.revature.springboot.Service;

import com.revature.springboot.Repository.CartRepo;
import com.revature.springboot.Repository.OrderHistoryRepo;
import com.revature.springboot.Repository.ProductRepo;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.CartItem;
import com.revature.springboot.model.CartItemRaw;
import com.revature.springboot.model.Order;
import com.revature.springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartService {

    @Autowired
    CartRepo cr;

    @Autowired
    ProductRepo pr;

    @Autowired
    OrderHistoryRepo ohr;


    public List<CartItem> getCart(int userId){
        return cr.findByUserId(userId);
        /*
        List<CartItem> userCart = cr.findByUserId(userId);

        List<Product> products = new ArrayList<Product>();
        for (CartItem item: userCart){
            products.add( item.getProduct() );
        }

        return products;

         */
    }

    public void addToCart(int userId, int prodId){
        Product product = pr.findById(prodId).get(0);

        CartItem newItem = new CartItem(userId, product);

        cr.save(newItem);
    }

//    public void adjustQuantity(CartItem item){
//        cr.save(item);
//    }

    public void adjustQuantity(CartItemRaw itemRaw){
        CartItem item;

        Optional<CartItem> repoItem = cr.findByUserIdAndProductId(itemRaw.getUserId(), itemRaw.getProductId());
        if (repoItem.isEmpty()){
            Product product = pr.findById(itemRaw.getProductId()).get(0);
            item = new CartItem(0, itemRaw.getUserId(), product, itemRaw.getQuantity());

        } else {
            item = repoItem.get();
            item.setQuantity(itemRaw.getQuantity());
        }
        System.out.println(item.getQuantity());
        cr.save(item);
    }

    @Transactional
    public void removeItem(CartItemRaw itemRaw){
        cr.deleteByUserIdAndProductId( itemRaw.getUserId(), itemRaw.getProductId() );
    }

    @Transactional
    public void checkout(int userID){
        List<CartItem> cart = getCart(userID);

        for (CartItem item : cart){
            ohr.save( new Order(item.getUserId(), item.getProduct(), item.getQuantity()) );
        }

        cr.deleteByUserId(userID);
    }

}
