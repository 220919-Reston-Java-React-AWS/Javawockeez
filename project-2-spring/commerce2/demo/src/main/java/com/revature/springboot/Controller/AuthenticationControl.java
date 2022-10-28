package com.revature.springboot.Controller;

import com.revature.springboot.Service.AuthenticationService;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.Response;
import com.revature.springboot.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthenticationControl {

    @Autowired
    private AuthenticationService AuthService;

//    @GetMapping(value="/test/{id}")
//    public ResponseEntity test(@PathVariable int id){
//        try {
//            User user = AuthService.getUserById(id);
//            user.secure();
//            return new ResponseEntity(user, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping(value = "/login", consumes="application/json")
    public ResponseEntity login(@RequestBody User user) {
        //public ResponseEntity login(@ String email, @RequestBody String password) {
        try {
            String email = user.getEmail();
            String password = user.getPassword();

            user = AuthService.login(email, password);

            System.out.println(user);

            return new ResponseEntity(user, HttpStatus.OK);

        } catch (QueryException e) {
            return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(new Response("Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
