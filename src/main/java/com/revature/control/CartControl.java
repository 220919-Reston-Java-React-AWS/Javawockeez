package com.revature.control;

import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import com.revature.model.Response;
import com.revature.service.CartService;
import io.javalin.Javalin;

import java.util.ArrayList;

public class CartControl {
    private CartService cs = new CartService();

    public CartControl(){

    }

    public void mapEndpoints(Javalin app){

        app.get("/cart/{email}", (ctx)->{
            try {
                String email = ctx.pathParam("email");
                ArrayList<Product> cart = cs.getCart(email);

                ctx.jsonStream(cart);
                ctx.status(200);

            } catch (QueryException e) {
                ctx.json( new Response( e.getMessage() ));
                ctx.status(400);

            } catch (Exception e) {
                ctx.json( new Response( e.getMessage() ));
                ctx.status(500);
            }
        });

        app.post("/cart/{email}/{product_id}", (ctx)->{
            try{
                String email = ctx.pathParam("email");
                String productID = ctx.pathParam("product_id");

                cs.addToCart(email, productID);

                ctx.json( new Response( "Item was added successfully" ) );
                ctx.status(200);

            } catch (Exception e) {
                ctx.json( new Response( e.getMessage() ) );
            }
        });

    }
}
