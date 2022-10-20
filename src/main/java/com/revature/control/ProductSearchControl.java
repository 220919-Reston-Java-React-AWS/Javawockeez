package com.revature.control;

import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import com.revature.model.Response;
import com.revature.service.ProductSearchService;

import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductSearchControl {

    private ProductSearchService pss = new ProductSearchService();

    public ProductSearchControl(){

    }

    public void mapEndpoints(Javalin app){

        app.get("/Products", (ctx)->{

            try{
                ArrayList productList = pss.getAllProducts();

                ctx.jsonStream(productList);
                ctx.status(200);
            } catch (Exception e) {
                ctx.json( new Response("An unexpected error has occurred.") );
            }

        });

        app.get("/Products/id={product_id}", (ctx)->{
            try{
                String productID = ctx.pathParam("product_id");
                Product product = pss.getProduct( Integer.parseInt(productID) );

                ctx.json(product);
                ctx.status(200);

            } catch (QueryException e){
                ctx.json( new Response( e.getMessage() ) );
                ctx.status(400);

            } catch (Exception e){
                ctx.json( new Response( e.getMessage() ) );
                ctx.status(500);
            }
        });

        app.get("/Products/category={category}", (ctx)->{

            try{
                String category = ctx.pathParam("category");
                ArrayList productList = pss.getProductsInCategory(category);

                ctx.jsonStream(productList);
                ctx.status(200);
            } catch (Exception e) {
                ctx.json(new Response("An unexpected error has occurred.") );
            }

        });

        app.get("/Products/search={keyword}", (ctx)->{

            try{
                String keyword = ctx.pathParam("keyword");
                ArrayList productList = pss.productSearch(keyword);

                ctx.jsonStream(productList);
                ctx.status(200);

            } catch (Exception e) {
                ctx.json(new Response("An unexpected error has occurred.") );
            }

        });

    }
}
