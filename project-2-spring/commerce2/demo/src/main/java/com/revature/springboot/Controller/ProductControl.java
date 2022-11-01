package com.revature.springboot.Controller;


import com.revature.springboot.Service.ProductService;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.Product;
import com.revature.springboot.model.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@CrossOrigin
@RestController
public class ProductControl {

    @Autowired
    private ProductService ps;// = new ProductSearchService();

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        try{
            List<Product> productList = ps.getAllProducts();
            return new ResponseEntity( productList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println( e.getMessage() );
            System.out.println( e.fillInStackTrace().toString() );
            return new ResponseEntity( new Response("An unexpected error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/id={product_id}")
    public ResponseEntity getSpecificProduct(@PathVariable String product_id) {
        try {
            Product product = ps.getProduct(Integer.parseInt(product_id));
            return new ResponseEntity(product, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/key={productKey}")
    public ResponseEntity getProductByStripeKey(@PathVariable String productKey) {
        try {
            Product product = ps.getProduct(productKey);
            return new ResponseEntity(product, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/category={category}")
    public ResponseEntity getProductsByCategory(@PathVariable String category){
        try{
            List<Product>  productList = ps.getProductsInCategory(category);
            return new ResponseEntity( productList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response("An unexpected error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/search={keyword}")
    public ResponseEntity getProductsByKeyword(@PathVariable String keyword){
        try{
            List<Product>  productList = ps.productSearch(keyword);
            return new ResponseEntity( productList, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity( new Response("An unexpected error has occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/stripe/{product_id}")
    public ResponseEntity getStripeKeyById(@PathVariable String product_id) {
        try {
            Product product = ps.getProduct(Integer.parseInt(product_id));
            return new ResponseEntity(product.getStripeKey(), HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
