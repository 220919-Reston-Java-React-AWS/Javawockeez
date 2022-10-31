package com.revature.springboot.Service;


import com.revature.springboot.Repository.CategoryRepo;
import com.revature.springboot.Repository.ProductRepo;
import com.revature.springboot.Repository.RatingRepo;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.Category;
import com.revature.springboot.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Component
public class ProductService {
    // -----------------------------------------         SET-UP         --------------------------------------------- //


    // The user repository to use. Spring should allow only one throughout the app (to save space)
    @Autowired
    ProductRepo pr;

    // The rating repository for average ratings
    @Autowired
    RatingRepo rr;

    // The category repository for seeing both product categories, and products in a category
    @Autowired
    CategoryRepo cr;

    // -----------------------------------------        GETTERS         --------------------------------------------- //

    public Product getProduct(int productID) throws QueryException {

        List<Product> productList = formatProductList( pr.findById(productID) );

        if (productList.isEmpty()){
            throw new QueryException("That product was not in the system");
        }
        return productList.get(0); // There really should only be one item in the list anyway
    }

    public List<Product> getAllProducts() throws QueryException {
        System.out.println(pr.findAll().size());
        return formatProductList( pr.findAll() );
    }

    public List<Product> getProductsInCategory(String key) throws QueryException {
        Category category = cr.findByCategoryIgnoreCase(key);
        return category.getProducts();
    }

    public List<Product> productSearch(String keyword) throws QueryException {
        return formatProductList( pr.findByNameContainingIgnoreCase(keyword) );
    }

    public String capitalize(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

    public List<Product> formatProductList(List<Product> products) throws QueryException {
        if (products.isEmpty()) {
            throw new QueryException("There are no products to display");
        }

        Optional<Double> avgRating;
        for (Product product : products) {
            avgRating = rr.findAverageRating(product.getId()); //Somewhat of a band-aid. not totally spring but much simpler
            if (!avgRating.isEmpty()){
                product.setAvgRating(avgRating.get());
            }
        }

        return products;
    }

}
