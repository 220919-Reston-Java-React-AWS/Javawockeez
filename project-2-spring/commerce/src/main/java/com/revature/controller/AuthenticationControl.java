package com.revature.controller;

import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.QueryException;
import com.revature.model.Response;
import com.revature.model.User;

import com.revature.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class AuthenticationControl {

    private AuthenticationService AuthService;// = new AuthenticationService();

    public AuthenticationControl(AuthenticationService AuthService){
        this.AuthService = AuthService;
    }

    @PostMapping(value = "/login", consumes="application/json")
    public ResponseEntity login(@RequestBody User user) {
    //public ResponseEntity login(@ String email, @RequestBody String password) {
        try {
            String email = user.getEmail();
            String password = user.getPassword();

            user = AuthService.login(email, password);

            return new ResponseEntity(user, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new Response("Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
