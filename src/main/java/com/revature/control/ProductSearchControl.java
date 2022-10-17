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
                ArrayList productList = pss.getAllTickets();

                ctx.jsonStream(productList);
                ctx.status(200);
            } catch (SQLException e) {
                ctx.result("An unexpected error has occurred.");
            }

        });

    }
}
