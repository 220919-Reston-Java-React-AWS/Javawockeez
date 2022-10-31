package com.revature.springboot.Controller;


import com.revature.springboot.Service.CartService;
import com.revature.springboot.model.CartItem;
import com.revature.springboot.model.CartItemRaw;
import com.revature.springboot.model.Product;
import com.revature.springboot.model.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
public class CartControl {

    @Autowired
    CartService cs;

    @GetMapping("/cart/{userId}")
    public ResponseEntity getCart(@PathVariable int userId){
        try {
            //List<Product> cart = cs.getCart(userId);
            List<CartItem> cart = cs.getCart(userId);

            return new ResponseEntity(cart, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/cart")
//    public ResponseEntity adjustQuantity(@RequestBody CartItem adjItem){
//        try {
//            cs.adjustQuantity(adjItem);
//
//            return new ResponseEntity(new Response( "Table was updated successfully" ), HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/cart")
    public ResponseEntity adjustQuantity(@RequestBody CartItemRaw adjItem){
        try {
            cs.adjustQuantity(adjItem);

            return new ResponseEntity(new Response( "Table was updated successfully" ), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cart")
    public ResponseEntity removeItem(@RequestBody CartItemRaw oldItem){
        try {
            cs.removeItem(oldItem);

            return new ResponseEntity(new Response( "Table was updated successfully" ), HttpStatus.OK);

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

    @DeleteMapping("cart/{userId}")
    public ResponseEntity checkout(@PathVariable int userId){
        try {
            cs.checkout(userId);
            return new ResponseEntity( new Response("Cart was deleted successfully"), HttpStatus.OK );

            //return new ResponseEntity(cart, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
