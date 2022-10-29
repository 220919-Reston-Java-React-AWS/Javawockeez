package com.revature.springboot.Repository;


import com.revature.springboot.model.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {

    @Query("SELECT avg(rating) FROM ratings WHERE product_id = ?1 GROUP BY product_id")
    public double findAverageRating(int productId);

}
