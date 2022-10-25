package com.revature.controller;

import com.revature.config.AppConfig;
import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import com.revature.model.Response;
import com.revature.service.ProductSearchService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class ProductControl {

    private ProductSearchService pss;// = new ProductSearchService();

    public ProductControl(ProductSearchService pss){
        this.pss = pss;
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        try{
            ArrayList productList = pss.getAllProducts();
            return new ResponseEntity( productList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response("An unexpected error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/id={product_id}")
    public ResponseEntity getSpecificProduct(@PathVariable String product_id) {
        try {
            Product product = pss.getProduct(Integer.parseInt(product_id));
            return new ResponseEntity(product, HttpStatus.OK);
        } catch (QueryException e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/category={category}")
    public ResponseEntity getProductsByCategory(@PathVariable String category){
        try{
            ArrayList productList = pss.getProductsInCategory(category);
            return new ResponseEntity( productList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response("An unexpected error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/search={keyword}")
    public ResponseEntity getProductsByKeyword(@PathVariable String keyword){
        try{
            ArrayList productList = pss.productSearch(keyword);
            return new ResponseEntity( productList, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity( new Response("An unexpected error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
