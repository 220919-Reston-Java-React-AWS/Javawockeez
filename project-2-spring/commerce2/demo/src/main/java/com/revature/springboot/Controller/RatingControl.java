package com.revature.springboot.Controller;


import com.revature.springboot.Service.RatingService;
import com.revature.springboot.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RatingControl {

    @Autowired
    RatingService rs;

    @PostMapping("/ratings")
    public ResponseEntity rateProduct(@RequestBody Rating rating){
        try{
            rs.rateProduct(rating);
            return new ResponseEntity( "Rating was successful", HttpStatus.OK );
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity( "The server has experienced an unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
