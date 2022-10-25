package com.revature.repository;

import com.revature.exceptions.QueryException;
import com.revature.model.Product;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Component
public class CartRepo {

    ProductRepo pr;// = new ProductRepo();

    public CartRepo(ProductRepo pr){
        this.pr = pr;
    }

    public void addToCart(int userID, int productID) throws SQLException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "INSERT INTO cart(user_id, product_id) VALUES (?,?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt( 1, userID );
            pstmt.setInt( 2, productID );

            pstmt.executeUpdate();
        }
    }


    public ArrayList<Product> getCart(int userID) throws SQLException, QueryException {
        try (Connection conn = ConnectionFactory.createConnection()){
            String sql = "SELECT products.id, product_name, brand, price, image_path, weight, description FROM cart INNER JOIN products ON cart.product_id=products.id WHERE cart.user_id=?;";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userID);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Product> productList = new ArrayList();
            int productID;
            while(rs.next()) {
                productID = rs.getInt(pr.ID_COLUMN);
                productList.add( new Product(
                        productID,
                        rs.getString(pr.PRODUCT_NAME_COLUMN),
                        rs.getString(pr.BRAND_COLUMN),
                        rs.getDouble(pr.PRICE_COLUMN),
                        rs.getString(pr.IMAGE_COLUMN),
                        rs.getDouble(pr.WEIGHT_COLUMN),
                        rs.getString(pr.DESCRIPTION_COLUMN),
                        pr.getAverageRating(productID)
                ) );
            }
            return productList;
        }
    }


    public static void main(String[] args) {

    }
}
