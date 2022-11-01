package com.revature.springboot.Repository;


import com.revature.springboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepo extends JpaRepository<Order, Integer> {

    public List<Order> findByUserId(int userId);
}
