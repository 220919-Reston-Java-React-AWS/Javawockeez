package com.revature.controller;

import com.revature.exceptions.InvalidInputException;
import com.revature.model.Response;
import com.revature.model.User;
import com.revature.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class RegistrationControl {

    RegistrationService rs = new RegistrationService();

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User newUser){
        try{
            rs.register(newUser);
            return new ResponseEntity( newUser, HttpStatus.OK);

        } catch (InvalidInputException e){
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.BAD_REQUEST );

        } catch (SQLException e) {
            return new ResponseEntity(new Response( "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR );

        } catch (Exception e) {
            return new ResponseEntity(new Response("The input could not be read by our system."), HttpStatus.BAD_REQUEST );
        }
    }

}
