package com.revature.springboot.Repository;


import com.revature.springboot.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUserId(int userId);
}
