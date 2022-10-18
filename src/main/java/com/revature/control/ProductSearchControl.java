package com.revature.control;

import com.revature.service.ProductSearchService;

import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductSearchControl {

    private ProductSearchService pss = new ProductSearchService();

    public ProductSearchControl(){

    }

    public void mapEndpoints(Javalin app){

        app.get("/allProducts", (ctx)->{

            try{
                ArrayList productList = pss.getAllProducts();

                ctx.jsonStream(productList);
                ctx.status(200);
            } catch (Exception e) {
                ctx.result("An unexpected error has occurred.");
            }

        });

        app.get("/allProducts/{category}", (ctx)->{

            try{
                String category = ctx.pathParam("category");
                ArrayList productList = pss.getProductsInCategory(category);

                ctx.jsonStream(productList);
                ctx.status(200);
            } catch (Exception e) {
                ctx.result("An unexpected error has occurred.");
            }

        });

    }
}
