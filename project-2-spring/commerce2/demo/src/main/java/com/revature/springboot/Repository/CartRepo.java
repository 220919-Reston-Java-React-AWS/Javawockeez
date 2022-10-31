package com.revature.springboot.Repository;


import com.revature.springboot.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUserId(int userId);

    public Optional<CartItem> findByUserIdAndProductId(int userId, int productId);

    public void deleteByUserIdAndProductId(int userId, int productId);

    @Modifying
    @Query("DELETE FROM cart WHERE user_id=?1")
    public void deleteByUserId(int userId);
}
