package com.revature.control;

import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.QueryException;
import com.revature.model.Response;
import com.revature.model.User;
import com.revature.service.AuthenticationService;

import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


// This is the control layer for authentication (logging in and out). It's where all input from 'online' is received and
// output is produced.
//
// There is one method - mapEndpoints, with endpoints for logging in, out, and seeing the current user.
// Endpoints: /login  /logout  /currentUser

public class AuthenticationControl {

    // Create a copy of the next layer to format the request.
    private AuthenticationService AuthService = new AuthenticationService();

    // Empty constructor
    public AuthenticationControl(){

    }

    public void mapEndpoints(Javalin app){

        //  ---------------  LOGIN  ----------------  //

        //Log in
        app.post("/login", (ctx)->{

            try{

                String email = ctx.formParam("email");
                String password = ctx.formParam("password");

                User newUser = AuthService.login(email, password);

                HttpSession session = ctx.req.getSession();
                session.setAttribute("user", newUser);

                ctx.json( new Response( String.format( "Login Successful, welcome %s!", newUser.toString() ) ) );
                ctx.status(200);

            } catch (SQLException e) {

                ctx.json( new Response("Server Error") );
                ctx.status(500);

            } catch (QueryException e) {

                ctx.json( new Response( e.getMessage() ) );
                ctx.status(400);

            } catch (InvalidInputException e) {

                ctx.json( new Response( e.getMessage() ) );
                ctx.status(400);

            }
        });

    }
}
