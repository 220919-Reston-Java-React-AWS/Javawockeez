package com.revature.service;

import com.revature.exceptions.QueryException;
import com.revature.model.User;
import com.revature.repository.UserRepo;

import io.javalin.http.Context;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

// This is the service layer for authentication. All requests to log in to the system are passed though here.
//
// Method:
//      login - Self-explanatory, logs the user in. (Logout is handled directly by the Control-Layer)

public class AuthenticationService {
    // -----------------------------------------         SET-UP         --------------------------------------------- //

    // Access to the User repository (or rather, access to the thing which accesses the database)
    UserRepo ur = new UserRepo();

    // Empty constructor - This isn't an 'object' so much as two methods.
    public AuthenticationService(){

    }


    // -----------------------------------------         LOGIN         ---------------------------------------------- //

    // login - Take the strings passed in for username and password, and verify
    public int login(String email, String password, Context ctx){

        try{

            if ( ur.checkPassword(email, password) ){  // Method checks for existence of the username (throws exception if not)

                // Create a user object to store in the current session - used for identification.
                User user = ur.getUserByEmail(email);

                // Store the user in the current session
                HttpSession session = ctx.req.getSession();
                session.setAttribute("user", user);

                return 0; // Success

            } else {
                return 1; // Wrong Password
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 500; // Internal Server Error - Somehow the parsing went wrong. This should not happen.

        } catch(QueryException e) {
            return 2; // Wrong Username
        }
    }

}
