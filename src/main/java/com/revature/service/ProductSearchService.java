package com.revature.service;

import com.revature.exceptions.QueryException;
import com.revature.repository.ProductRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductSearchService {

    private ProductRepo pr = new ProductRepo();

    public ProductSearchService(){

    }

    public ArrayList getAllProducts() throws SQLException, QueryException {
        return pr.getAllProducts();
    }

    public ArrayList getProductsInCategory(String key) throws SQLException, QueryException {
        int categoryID = getCategoryID(key);
        return pr.getProductsInCategory(categoryID);
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
