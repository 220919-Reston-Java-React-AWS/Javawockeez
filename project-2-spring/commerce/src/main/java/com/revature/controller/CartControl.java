package com.revature.controller;

import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import com.revature.model.Response;
import com.revature.service.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class CartControl {

    CartService cs;// = new CartService();

    public CartControl(CartService cs){
        this.cs = cs;
    }

    @GetMapping("/cart/{email}")
    public ResponseEntity getCart(@PathVariable String email){
        try {
            ArrayList<Product> cart = cs.getCart(email);

            return new ResponseEntity(cart, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cart/{email}/{product_id}")
    public ResponseEntity newCartItem(@PathVariable String email, @PathVariable String product_id){
        try{
            cs.addToCart(email, product_id);
            return new ResponseEntity<>( new Response( "Item was added successfully" ), HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<>( new Response( e.getMessage() ), HttpStatus.BAD_REQUEST );
        }
    }

}
