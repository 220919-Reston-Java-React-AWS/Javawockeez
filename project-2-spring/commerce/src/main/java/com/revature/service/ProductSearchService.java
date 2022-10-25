package com.revature.service;

import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import com.revature.repository.ProductRepo;

import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;


@Component
public class ProductSearchService {

    private ProductRepo pr;// = new ProductRepo();

    public ProductSearchService(ProductRepo pr){
        this.pr = pr;
    }

    public Product getProduct(int productID) throws SQLException, QueryException {
        return pr.getProduct(productID);
    }

    public ArrayList getAllProducts() throws SQLException, QueryException {
        return pr.getAllProducts();
    }

    public ArrayList getProductsInCategory(String key) throws SQLException, QueryException {
        int categoryID = getCategoryID(key);
        return pr.getProductsInCategory(categoryID);
    }

    public ArrayList productSearch(String keyword) throws SQLException, QueryException {
        return pr.getProductsWithNameContaining(keyword);
    }

    public int getCategoryID(String key) throws SQLException, QueryException {
        return pr.getCategoryID( capitalize(key) );
    }

    public String capitalize(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }
}
