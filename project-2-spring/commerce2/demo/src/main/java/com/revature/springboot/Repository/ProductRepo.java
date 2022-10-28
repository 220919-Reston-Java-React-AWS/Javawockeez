package com.revature.springboot.Repository;

import com.revature.springboot.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {


    public List<Product> findAll();
    public List<Product> findById(int id);

    public List<Product> findByNameContainingIgnoreCase(String keyword);

//    @Query("SELECT avg(rating) FROM ratings WHERE product_id = ?1 GROUP BY product_id")
//    public int getAvgRating(int id);
//
//    @Query("SELECT products.id, product_name, brand, price, image_path, weight, description FROM products " +
//            "INNER JOIN product_categories " +
//            "ON products.id=product_categories.product_id " +
//            "WHERE category_id=?1")
//    public Optional<List<Product>> getProductsByCategory(int category);
//
////    @Query("SELECT * FROM products WHERE POSITION( ?1 IN lower(product_name) )!=0;")
////    public Optional<List<Product>> getProductsByKeyword(String keyword);
//
//    @Query("SELECT id FROM categories WHERE category=?1")
//    public Optional<List<Integer>> getCategoryID(String category);

}
