package com.revature.springboot.Service;

import com.revature.springboot.Repository.RatingRepo;
import com.revature.springboot.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatingService {

    @Autowired
    RatingRepo rr;

    public void rateProduct(Rating rating){
        rr.save(rating);
    }

}
