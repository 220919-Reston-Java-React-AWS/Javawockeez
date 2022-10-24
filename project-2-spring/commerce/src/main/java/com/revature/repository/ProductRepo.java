package com.revature.repository;

import com.revature.exceptions.QueryException;
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


    public ProductRepo(){

    }


    public Product getProduct(int productID) throws SQLException, QueryException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM products WHERE id=?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productID);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        productID,
                        rs.getString(PRODUCT_NAME_COLUMN),
                        rs.getString(BRAND_COLUMN),
                        rs.getDouble(PRICE_COLUMN),
                        rs.getString(IMAGE_COLUMN),
                        rs.getDouble(WEIGHT_COLUMN),
                        rs.getString(DESCRIPTION_COLUMN),
                        getAverageRating(productID)
                );
            } else {
                throw new QueryException("That product ID is not in the database");
            }
        }
    }

    // Creates a table of information about every ticket in the system.
    public ArrayList getAllProducts() throws SQLException, QueryException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM products;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Product> productList = new ArrayList();
            int productID;
            while(rs.next()) {
                productID = rs.getInt(ID_COLUMN);
                productList.add( new Product(
                        productID,
                        rs.getString(PRODUCT_NAME_COLUMN),
                        rs.getString(BRAND_COLUMN),
                        rs.getDouble(PRICE_COLUMN),
                        rs.getString(IMAGE_COLUMN),
                        rs.getDouble(WEIGHT_COLUMN),
                        rs.getString(DESCRIPTION_COLUMN),
                        getAverageRating(productID)
                ) );
            }
            return productList;
        }
    }

    public ArrayList getProductsInCategory(int category_id) throws SQLException, QueryException { // A non-existent key will return an empty list
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT products.id, product_name, brand, price, image_path, weight, description FROM products " +
                    "INNER JOIN product_categories " +
                    "ON products.id=product_categories.product_id " +
                    "WHERE category_id=?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, category_id);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Product> productList = new ArrayList();
            int productID;
            while(rs.next()) {
                productID = rs.getInt(ID_COLUMN);
                productList.add( new Product(
                        productID,
                        rs.getString(PRODUCT_NAME_COLUMN),
                        rs.getString(BRAND_COLUMN),
                        rs.getDouble(PRICE_COLUMN),
                        rs.getString(IMAGE_COLUMN),
                        rs.getDouble(WEIGHT_COLUMN),
                        rs.getString(DESCRIPTION_COLUMN),
                        getAverageRating(productID)
                ) );
            }
            return productList;
        }
    }

    public ArrayList getProductsWithNameContaining(String keyword) throws SQLException, QueryException { //keyword must be lowercase to work
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM products WHERE POSITION( ? IN lower(product_name) )!=0;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, keyword);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Product> productList = new ArrayList();
            int productID;
            while(rs.next()) {
                productID = rs.getInt(ID_COLUMN);
                productList.add( new Product(
                        productID,
                        rs.getString(PRODUCT_NAME_COLUMN),
                        rs.getString(BRAND_COLUMN),
                        rs.getDouble(PRICE_COLUMN),
                        rs.getString(IMAGE_COLUMN),
                        rs.getDouble(WEIGHT_COLUMN),
                        rs.getString(DESCRIPTION_COLUMN),
                        getAverageRating(productID)
                ) );
            }
            return productList;
        }
    }

    public double getAverageRating(int product_id) throws SQLException, QueryException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT avg(rating) FROM ratings WHERE product_id = ? GROUP BY product_id;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, product_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);

            } else {
                throw new QueryException("That product ID is not in the database");
            }
        }
    }

    public int getCategoryID(String key) throws SQLException, QueryException { // key must be capitalized first, i.e. Bread, not bread, BREAD, etc.
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT id FROM categories WHERE category=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, key);

            ResultSet rs = pstmt.executeQuery();
            if ( rs.next() ){
                return rs.getInt(1); // Only one column, ID

            } else { //No results -> not a category
                throw new QueryException("There are no results for " + key);
            }
        }
    }

    public static void main(String[] args) {
        ProductRepo pr = new ProductRepo();

        try{

            //ArrayList allProducts = pr.getAllProducts();
            //ArrayList allProducts = pr.getProductsInCategory(1);
            //ArrayList allProducts = pr.getProductsWithNameContaining("chocolate");
            /*
            for (Object p: allProducts){
                System.out.println(p);
            }*/

            //System.out.println( pr.getCategoryID("Cake") );
            //System.out.println( pr.getAverageRating(2) );
            System.out.println( pr.getProduct(99) );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
