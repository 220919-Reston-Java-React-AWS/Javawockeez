package com.revature.control;

import com.revature.exceptions.InvalidInputException;
import com.revature.model.User;
import com.revature.service.RegistrationService;

import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RegistrationControl {
    // Create a copy of the nest layer, which formats or requests
    RegistrationService rs = new RegistrationService();

    // Empty Constructor
    public RegistrationControl(){

    }

    public void mapEndpoints(Javalin app){

        //  ---------------  REGISTER  ----------------  //

        app.post("/register", (ctx->{

            try {
                //Get the new user
                //newUser = ctx.bodyAsClass(User.class);

                String email = ctx.formParam("email");
                String password = ctx.formParam("password");
                String firstname = ctx.formParam("firstname");
                String lastname = ctx.formParam("lastname");

                User newUser = new User(email, password, firstname, lastname, 1);
                rs.register(newUser);

                // Login after registering
                HttpSession session = ctx.req.getSession();
                session.setAttribute("user", newUser);

                ctx.result("You have successfully been registered");
                ctx.status(200);

            } catch (InvalidInputException e){

                ctx.result( e.getMessage() );
                ctx.status(400);

            } catch (SQLException e) {

                System.out.println(e.getMessage());
                ctx.result("Internal Server Error");
                ctx.status(500);

            } catch (Exception e) {

                System.out.println(e.getMessage());
                ctx.result("The input could not be read by our system.");
                ctx.status(400);

            }

        }));
    }
}
