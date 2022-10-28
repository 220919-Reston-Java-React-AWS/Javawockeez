package com.revature.springboot.Controller;


import com.revature.springboot.Service.ProfileService;
import com.revature.springboot.exceptions.QueryException;
import com.revature.springboot.model.Response;
import com.revature.springboot.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProfileControl {

    @Autowired
    ProfileService ps;


    @GetMapping(value = "/profile/{userId}")
    public ResponseEntity getProfile(@PathVariable int userId){
        try {
            User user = ps.getProfile(userId);
            return new ResponseEntity(user, HttpStatus.OK);

        } catch (QueryException e){
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.NOT_FOUND );
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity( new Response( "Internal Service Error"), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PatchMapping(value = "/profile/{userId}", consumes = "application/json")
    public ResponseEntity updateProfile(@PathVariable int userId, @RequestBody User updatedUser){

        try{
            return new ResponseEntity( ps.updateProfile(userId, updatedUser), HttpStatus.OK );

        } catch (QueryException e) {
            return new ResponseEntity( new Response( e.getMessage() ), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity( new Response( "Internal Service Error"), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

}
