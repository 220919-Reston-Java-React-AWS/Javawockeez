package com.revature.springboot.Repository;

import com.revature.springboot.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    public List<Product> findById(int id);

    public List<Product> findByStripeKey(String stripeKey);

    public List<Product> findByNameContainingIgnoreCase(String keyword);


}
