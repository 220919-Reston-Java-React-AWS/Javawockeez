package com.revature.Controller;

import com.revature.Model.Account;
import com.revature.Service.AuthService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;

public class AuthController {

    private AuthService as = new AuthService();

    public void mapEndpoints(Javalin app){

        // PATCH - update an Account
        app.patch("/update-account-name", (ctx)->{
            // get a JSON in the request body and place it into an Account object
            Account requestJSON = ctx.bodyStreamAsClass(Account.class);

//            System.out.println(requestJSON.getFirstName());
//            System.out.println(requestJSON.getLastName());

            // try updating a new account/object in database
            try{
                as.updateAccountName(requestJSON);

                ctx.status(200);    // 200 okay
            }
            catch (Exception e){
                ctx.status(500); // server fail
            }
        });

        // read/get the first name and last of a username & password
        // GET with fetch prevents having a json body, so use the uri instead
        app.get("/read-name-{username}-{password}", (ctx)->{
            // get a JSON in the request body and place it into an Account object
            Account requestJSON = new Account();

            requestJSON.setUsername(ctx.pathParam("username"));
            requestJSON.setPassword(ctx.pathParam("password"));

            // try creating a new account/object in database
            try{
                Account account = as.getName(requestJSON);

                ctx.json(account);
                ctx.status(200);    // 200 ok
            }
            catch (Exception e){
                ctx.status(500); // server fail
            }
        });

        // POST - create a new Account
        app.post("/create", (ctx)->{
            // get a JSON in the request body and place it into an Account object
            Account requestJSON = ctx.bodyStreamAsClass(Account.class);

            // try creating a new account/object in database
            try{
                as.createAccount(requestJSON);

                ctx.status(201);    // 201 created
            }
            catch (Exception e){
                ctx.status(500); // server fail
            }
        });

        // DELETE - delete an Account
        app.delete("/delete-account", (ctx)->{
            // get a JSON in the request body and place it into an Account object
            Account requestJSON = ctx.bodyStreamAsClass(Account.class);

            // try creating a new account/object in database
            try{
                as.deleteAccount(requestJSON);

                ctx.status(200);    // 200 ok
            }
            catch (Exception e){
                ctx.status(500); // server fail
            }
        });

    }
}
