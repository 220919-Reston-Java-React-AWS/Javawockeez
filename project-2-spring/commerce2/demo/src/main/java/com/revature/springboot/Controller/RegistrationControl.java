package com.revature.springboot.Controller;


import com.revature.springboot.Service.RegistrationService;
import com.revature.springboot.exceptions.InvalidInputException;
import com.revature.springboot.model.Response;
import com.revature.springboot.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class RegistrationControl {

    @Autowired
    RegistrationService rs;// = new RegistrationService();


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User newUser){
        try{
            rs.register(newUser);
            newUser.secure();
            return new ResponseEntity( newUser, HttpStatus.OK);

        } catch (InvalidInputException e){
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.BAD_REQUEST );

        } catch (Exception e) {
            return new ResponseEntity(new Response("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
