package com.revature.service;

import com.revature.repository.ProductRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductSearchService {

    private ProductRepo pr = new ProductRepo();

    public ProductSearchService(){

    }

    public ArrayList getAllTickets() throws SQLException {
        return pr.getAllTickets();
    }
}
