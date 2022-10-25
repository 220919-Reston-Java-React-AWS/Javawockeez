package com.revature.service;

import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import com.revature.repository.CartRepo;
import com.revature.repository.UserRepo;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;


@Component
public class CartService {

    private CartRepo cr;// = new CartRepo();
    private UserRepo ur;// = new UserRepo();

    public CartService(CartRepo cr, UserRepo ur){
        this.cr = cr;
        this.ur = ur;
    }

    public ArrayList<Product> getCart(String email) throws SQLException, QueryException {
        int userID = ur.getUserIDByEmail(email);
        return cr.getCart(userID);
    }

    public void addToCart(String email, String productID) throws SQLException, QueryException {
        int userID = ur.getUserIDByEmail(email);
        int pID = Integer.parseInt(productID);
        cr.addToCart(userID, pID);
    }
}
