package com.revature.springboot.Controller;


import com.revature.springboot.Service.OrderHistoryService;
import com.revature.springboot.model.CartItem;
import com.revature.springboot.model.Order;
import com.revature.springboot.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class OrderHistoryControl {

    @Autowired
    OrderHistoryService ohs;

    @GetMapping("/history/{userId}")
    public ResponseEntity getOrderHistory(@PathVariable int userId){
        try {
            List<Order> orders = ohs.getOrderHistory(userId);

            return new ResponseEntity(orders, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
