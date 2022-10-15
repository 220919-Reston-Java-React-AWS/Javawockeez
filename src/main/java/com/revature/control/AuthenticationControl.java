package com.revature.control;

import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.QueryException;
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

                ctx.result( String.format( "Login Successful, welcome %s!", newUser.toString() ) );
                ctx.status(200);

            } catch (SQLException e) {

                ctx.result("Server Error");
                ctx.status(500);

            } catch (QueryException e) {

                ctx.result(e.getMessage());
                ctx.status(400);

            } catch (InvalidInputException e) {

                ctx.result(e.getMessage());
                ctx.status(400);

            }
        });

        /*
        // This should all be done in front end.

        // Log in help response.
        app.get("/login", (ctx)->{
            ctx.html("<p>Enter yer username and password to login.<br> " +
                    //"<form method=\"post\" action=\"/login\" onsubmit=\"return false;\">" +
                    "<form method=\"post\" action=\"/login\">" +
                    "  <label for=\"fname\">Username:</label>" +
                    "  <input type=\"text\" id=\"usr\" name=\"usr\"<br><br>" +
                    "  <label for=\"lname\">Password:</label>" +
                    "  <input type=\"text\" id=\"pwd\" name=\"pwd\"><br><br>" +
                    "  <input type=\"submit\" value=\"Submit\">" +
                    "</form>");
        });




        //  ---------------  LOGOUT  ---------------  //

        // Log out help response
        app.get("/logout", (ctx)->{
            ctx.result("Post to this page to log out");
            ctx.status(200);
        });


         */

        // Logout and terminate session
        app.post("/logout", (ctx)->{
            ctx.req.getSession().invalidate(); // No point going to auth.service or beyond for this.
            ctx.result("You have successfully logged out.");
            ctx.status(200);
        });


        //  ------------  SEE CURRENT USER  ------------  //

        // See who's currently logged in
        app.get("/currentUser", (ctx)->{
            HttpSession httpSession = ctx.req.getSession();

            User user = (User) httpSession.getAttribute("user");

            if (user==null){
                ctx.result("You are not logged in");
            } else {
                ctx.result( user.toString() );
            }
        });
    }
}
