package com.revature.springboot.Controller;


import com.revature.springboot.Service.CartService;

import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.Product;
import com.revature.springboot.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class CartControl {

    @Autowired
    CartService cs;

    @GetMapping("/cart/{userId}")
    public ResponseEntity getCart(@PathVariable int userId){
        try {
            List<Product> cart = cs.getCart(userId);

            return new ResponseEntity(cart, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cart/{userId}/{productId}")
    public ResponseEntity newCartItem(@PathVariable int userId, @PathVariable int productId){
        try{
            cs.addToCart(userId, productId);
            return new ResponseEntity<>( new Response( "Item was added successfully" ), HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<>( new Response( e.getMessage() ), HttpStatus.BAD_REQUEST );
        }
    }

}
