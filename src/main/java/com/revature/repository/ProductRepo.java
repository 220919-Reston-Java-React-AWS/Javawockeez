package com.revature.repository;

import com.revature.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class ProductRepo {
    // Column positions in the SQL database, used to simplify minor changes.
    public final int ID_COLUMN = 1;
    public final int PRODUCT_NAME_COLUMN = 2;
    public final int BRAND_COLUMN = 3;
    public final int PRICE_COLUMN = 4;
    public final int IMAGE_COLUMN = 5;
    public final int WEIGHT_COLUMN = 6;
    public final int DESCRIPTION_COLUMN = 7;


    // Creates a table of information about every ticket in the system.
    public ArrayList getAllTickets() throws SQLException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM products;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Product> productList = new ArrayList();
            while(rs.next()) {
                productList.add( new Product(
                        rs.getInt(ID_COLUMN),
                        rs.getString(PRODUCT_NAME_COLUMN),
                        rs.getString(BRAND_COLUMN),
                        rs.getDouble(PRICE_COLUMN),
                        rs.getString(IMAGE_COLUMN),
                        rs.getDouble(WEIGHT_COLUMN),
                        rs.getString(DESCRIPTION_COLUMN)
                ) );
            }
            return productList;
        }
    }

    public static void main(String[] args) {
        ProductRepo pr = new ProductRepo();

        try{
            ArrayList allProducts = pr.getAllTickets();
            for (Object p: allProducts){
                System.out.println(p);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
