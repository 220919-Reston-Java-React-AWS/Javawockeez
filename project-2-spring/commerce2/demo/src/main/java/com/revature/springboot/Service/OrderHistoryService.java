package com.revature.springboot.Service;


import com.revature.springboot.Repository.OrderHistoryRepo;
import com.revature.springboot.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryService {

    @Autowired
    OrderHistoryRepo ohr;

    public List<Order> getOrderHistory(int userId){
        return ohr.findByUserId(userId);
    }
}
